(defproject pmud "0.1.0-SNAPSHOT"
  :plugins [[lein-cljsbuild "1.0.3"]]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:output-to "../www/js/gen/dev.js"
                                   :output-dir "../www/js/gen"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "release"
                        :source-paths ["src"]
                        :compiler {:output-to "../www/js/gen/release.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :preamble ["react/react.min.js"]
                                   :externs ["react/externs/react.js"]}}]}
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.7"]]
                   :source-paths ["dev"]}}
  :main pmud.web.server
  :exclusions [org.clojure/clojure]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojars.franks42/cljs-uuid-utils "0.1.3"]
                 [environ "1.0.0"]
                 [its-log "0.2.2"]
                 [clojurewerkz/neocons "3.0.0"]
                 [crypto-password "0.1.3"]
                 [ring "1.3.1"]
                 [ring-cors "0.1.4"]
                 [ring.middleware.jsonp "0.1.6"]
                 [org.clojars.mikejs/ring-gzip-middleware "0.1.0-SNAPSHOT"]
                 [http-kit "2.1.19"]
                 [compojure "1.2.0"]
                 [om "0.7.3"]
                 [racehub/om-bootstrap "0.3.1"]
                 [sablono "0.2.22"]])

