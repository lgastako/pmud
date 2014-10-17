(ns pmud.web.server
  (:gen-class)
  (:require [clojure.core.async :refer [go]]
            [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [environ.core :refer [env]]
            [its.log :as log]
            [org.httpkit.server :as kit]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.jsonp :refer [wrap-json-with-padding]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]))

(log/set-level! :debug)
(log/debug :pmud.server :begin)

(defroutes routes
  (let [home-page (slurp (io/resource "public/index.html"))
        not-found-page (slurp (io/resource "public/404.html"))]
    (GET "/" [] home-page)
    (GET "/abc" [] home-page)
    (route/not-found not-found-page)))

(def site
  (-> routes
      (wrap-json-with-padding)
      (wrap-cors)
      (wrap-keyword-params)
      (wrap-multipart-params)
      (wrap-params)
      (wrap-stacktrace)
      (wrap-gzip)
      (wrap-not-modified)
      (wrap-reload)))

(defn serve [port]
  (kit/run-server #'site {:port port}))

(defn -main [& args]
  (let [argc (count args)]
    (when (not= argc 1)
      (println "Usage: server [<port>]")
      (System/exit 1))
    (let [port (Integer. (first args))]
      (log/info :pmud.server :listen port)
      (serve port))))

(log/debug :pmud.server :end)
