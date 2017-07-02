(ns logiconf.core
  (:require [mount.core :as mount :refer [defstate]]
            [clojure.tools.logging :as log]
            [logiconf.util :as util]
            [immutant.web :as web]
            [logiconf.handler :refer (app-handler)])
  (:gen-class))

;; 配置
(defstate config
  :start (apply merge (for [ps [(System/getProperties)
                                (util/load-dotenv)
                                (System/getenv)]]
                        (into {} (for [[k v] ps]
                                   [(keyword k) v])))))

;; web服务器
(defstate ^{:on-reload :noop} web-server
  :start (web/run app-handler {:host (:LOGICONF_HOST config "localhost")
                               :port (:LOGICONF_PORT config 8080)})
  :stop  (web/stop web-server))

(defn is-prod-mode? []
  (= "prod" (:LOGICONF_MODE config "prod")))

(defn is-dev-mode? []
  (= "dev" (:LOGICONF_MODE config "prod")))

(defn start [& args]
  (mount/start))

(defn stop []
  (mount/stop))

(defn restart []
  (stop)
  (start))

(def -main start)
