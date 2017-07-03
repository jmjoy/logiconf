(ns logiconf.core
  (:require [mount.core :as mount :refer [defstate]]
            [clojure.tools.logging :as log]
            [logiconf.util :as util]
            [immutant.web :as web]
            [hikari-cp.core :as cp]
            [logiconf.handler :as handler]
            [korma.db :as db])
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

(defstate ^{:dov "WEB服务器" :on-reload :noop} web-server
  :start (web/run (if (is-dev-mode?)
                    handler/app-dev-handler
                    handler/app-handler)
                  {:host (:LOGICONF_HOST config "localhost")
                   :port (:LOGICONF_PORT config 8080)})

  :stop  (web/stop web-server))

(defstate ^{:doc "MySQL连接池" :on-reload :noop} mysql-datasource
  :start (let [datasource {:datasource (cp/make-datasource
                                        {:adapter            "mysql"
                                         :username           (or (:LOGICONF_MYSQL_USERNAME config) (throw (Exception. "Must specify mysql username")))
                                         :password           (or (:LOGICONF_MYSQL_PASSWORD config) (throw (Exception. "Must specify mysql password")))
                                         :database-name      (or (:LOGICONF_MYSQL_DATABASE config) (throw (Exception. "Must specify mysql database")))
                                         :server-name        (:LOGICONF_MYSQL_SERVER config "127.0.0.1")
                                         :port-number        (:LOGICONF_MYSQL_PORT config 3306)})}]
           (db/default-connection (db/create-db datasource))
           datasource)

  :stop (cp/close-datasource (:datasource mysql-datasource)))

(defn start [& args]
  (mount/start))

(defn stop []
  (mount/stop))

(defn restart []
  (stop)
  (start))

(def -main start)
