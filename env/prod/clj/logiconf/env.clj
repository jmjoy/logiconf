(ns logiconf.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[logiconf started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[logiconf has shut down successfully]=-"))
   :middleware identity})
