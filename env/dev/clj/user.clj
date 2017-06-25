(ns user
  (:require [mount.core :as mount]
            logiconf.core))

(defn start []
  (mount/start-without #'logiconf.core/repl-server))

(defn stop []
  (mount/stop-except #'logiconf.core/repl-server))

(defn restart []
  (stop)
  (start))


