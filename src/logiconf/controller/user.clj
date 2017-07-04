(ns logiconf.controller.user
  (:require [compojure.core :refer [context GET]]))

(def user
  (context "/user" []
           (GET "/" [] "user page.")))
