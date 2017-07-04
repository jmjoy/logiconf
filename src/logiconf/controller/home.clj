(ns logiconf.controller.home
  (:require [compojure.core :refer [defroutes GET routes]]
            [compojure.core :refer [context]]))

(def home
  (context "/" []
           (GET "/" [] "home page.")
           (GET "/about" [] "about page.")))
