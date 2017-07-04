(ns logiconf.handler
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [prone.middleware :refer [wrap-exceptions]]
            [compojure.core :refer [routes]]
            [compojure.route :as route]))

(def app-routes
  (apply routes
         ;; 动态获取controller的路由
         (concat (filter (complement nil?)
                         (for [ns (all-ns)
                               :when (.startsWith (str ns) "logiconf.controller")]
                           (ns-resolve ns (symbol "actions"))))
                 [;; 静态资源
                  (route/resources "/")
                  ;; 404
                  (route/not-found "page not found.")])))


(def app-handler #'app-routes)


(def app-dev-handler (-> #'app-handler
                         wrap-reload
                         wrap-exceptions))
