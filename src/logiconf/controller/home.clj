(ns logiconf.controller.home
  (:require [compojure.core :refer [GET routes context]]))

(def actions
  (context "/" []
           (GET "/" [] "home page.")
           (GET "/about" [] "about page.")))
