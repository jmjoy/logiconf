(ns logiconf.core
  (:require [mount.core :as mount :refer [defstate]]
            [logiconf.util :as util])
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
