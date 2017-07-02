(ns logiconf.core
  (:require [mount.core :as mount :refer [defstate]]
            [clojure.tools.logging :as log]
            [logiconf.util :as util])
  (:import [org.apache.logging.log4j LogManager])
  (:gen-class))

;; 配置
(defstate config :start (apply merge (for [ps [(System/getProperties)
                                               (util/load-dotenv)
                                               (System/getenv)]]
                                       (into {} (for [[k v] ps]
                                                  [(keyword k) v])))))

(defn is-prod-mode? []
  (= "prod" (:LOGICONF_MODE config "prod")))

(defn is-dev-mode? []
  (= "dev" (:LOGICONF_MODE config "prod")))

(defn start []
  (mount/start))

(defn stop []
  (mount/stop))

(defn restart []
  (stop)
  (start))

(def -main start)

(log/error "test")

(let [logger (LogManager/getLogger (str *ns*))]
  (.error logger "hehe"))
