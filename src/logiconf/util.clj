(ns logiconf.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [java.util Properties]))

(defmacro do-print [& args]
  "打印表达式的值。"
  `(let [expr# ~args] (println expr#) expr#))

(defn load-dotenv
  "加载.env文件到系统变量。"
  ([] (load-dotenv ".env"))
  ([filename]
   (if-let [f (io/resource filename)]
     (let [p (Properties.)]
       (with-open [rdr (io/reader f)]
         (.load p rdr))
       p))))

(load-dotenv)
