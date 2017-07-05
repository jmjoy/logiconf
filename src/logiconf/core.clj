(ns logiconf.core
  (:require [mount.core :as mount :refer [defstate]]
            [clojure.tools.logging :as log]
            [logiconf.util :as util]
            [immutant.web :as web]
            [hikari-cp.core :as cp]
            [logiconf.handler :as handler]
            [korma.db :as db]
            [migratus.core :as migratus])
  (:gen-class))

(defstate ^{:doc "配置"} config
  :start (apply merge (for [ps [(System/getProperties)
                                (util/load-dotenv)
                                (System/getenv)]]
                        (into {} (for [[k v] ps]
                                   [(keyword k) v])))))

(defn is-dev-mode? []
  "是不是测试环境？"
  (= "dev" (:LOGICONF_MODE config "prod")))

(defn get-jdbc-url []
  "获取jdbc路径"
  (or (:LOGICONF_JDBC_URL config) (throw (Exception. "Must specify jdbc url."))))

(defstate ^{:dov "WEB服务器" :on-reload :noop} web-server
  :start (web/run (if (is-dev-mode?)
                    handler/app-dev-handler
                    handler/app-handler)
                  {:host (:LOGICONF_HOST config "localhost")
                   :port (:LOGICONF_PORT config 8080)})

  :stop  (web/stop web-server))

(defstate ^{:doc "MySQL连接池" :on-reload :noop} mysql-datasource
  :start (let [datasource {:datasource (cp/make-datasource {:jdbc-url (get-jdbc-url)})}]
           (db/default-connection (db/create-db datasource))
           datasource)

  :stop (cp/close-datasource (:datasource mysql-datasource)))

(defstate ^{:doc "数据库迁移配置"} migratus-config
  :start {:store :database
          :migration-dir "migrations/"
          ;; :init-script "init.sql"
          ;; :migration-table-name "migration"
          :db {:connection-uri (get-jdbc-url)}})

(defn start [& args]
  (mount/start))

(defn stop []
  (mount/stop))

(defn restart []
  (stop)
  (start))

(def -main start)
