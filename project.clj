(defproject logiconf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [mount "0.1.11"]
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.immutant/immutant "2.1.9"]
                 [ring "1.6.1"]
                 [compojure "1.6.0"]
                 [prone "1.1.4"]
                 [korma "0.4.0"]
                 [hikari-cp "1.7.6"]
                 [mysql/mysql-connector-java "5.1.41"]
                 [migratus "0.9.7"]]
  :main ^:skip-aot logiconf.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
