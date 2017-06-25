(ns logiconf.routes.home
  (:require [logiconf.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/test" [] (layout/render "test"))
  (GET "/about" [] (about-page)))

