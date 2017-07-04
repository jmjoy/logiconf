(ns logiconf.controller.core
  (:require [compojure.core :refer [defroutes GET routes]]
            [compojure.route :as route]
            [logiconf.controller.home :refer [home]]
            [logiconf.controller.user :refer [user]]))

(defroutes handler
  home
  user
  (route/resources "/")
  (route/not-found "page not found."))
