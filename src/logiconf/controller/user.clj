(ns logiconf.controller.user
  (:require [compojure.core :refer [GET routes context]]))

(def actions
  (context
   "/user" []
   (GET "/login" request (str request))))
