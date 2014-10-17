(ns pmud.db
  (:require [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.nodes :as nn]))

(def ^:dynamic *conn*)

(defn connect
  ([] (connect "http://pmud:pmud@localhost/db/data/"))
  ([url] (set! *conn* (nr/connect url))))

(defn init []
  (connect))

(defn create-node [& args]
  (apply nn/create *conn* args))

(defn remove-node [type id]
  (let [node (nn/get *conn* id)]
    (when (= type (:type node))
      (nn/delete *conn* node))))
