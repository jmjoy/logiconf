(ns logiconf.handler
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.]
            [logiconf.controller.core :refer [handler]]))

(def app-handler handler)

(def app-dev-handler (-> #'app-handler
                         (wrap-reload)))
