(ns logiconf.handler
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [prone.middleware :refer [wrap-exceptions]]
            [logiconf.controller.core :refer [handler]]))

(def app-handler #'handler)

(def app-dev-handler (-> #'app-handler
                         wrap-reload
                         wrap-exceptions))
