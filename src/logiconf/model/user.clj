(ns logiconf.model.user
  (:require [clojure.java.jdbc :as jdbc]
            [logiconf.core :refer [mysql-datasource]]))


(jdbc/query mysql-datasource "select 1")

;; (jdbc/execute! mysql-datasource (jdbc/create-table-ddl :user
;;                         [:id :int :primary :key]
;;                         [:name "varchar(64) NOT NULL DEFAULT 0"]))

