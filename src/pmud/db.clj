(ns pmud.db
  (:require [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.nodes :as nn]
            [its.log :as log]))

(def DEFAULT_URL "http://127.0.0.1:7474/db/data/")

(def ^:dynamic *conn*)

(defn connect
  ([] (connect DEFAULT_URL))
  ([url]
     (log/debug :pmud.db/connect {:url url})
     (def ^:dynamic *conn* (nr/connect url))))

(defn create-node [props]
  (nn/create *conn* props))

(defn get-node [id]
  (try
    (nn/get *conn* id)
    (catch Exception ex
      (let [data (.getData ex)
            status (:status data)]
        (log/debug :status status)
        (when (not= 404 (:status data))
          (throw ex))))))

(defn remove-node [type id]
  (log/debug :remove-node {:type type :id id})
  (let [node (nn/get *conn* id)]
    (log/debug :found-node node)
    (when (= type (:type (:data node)))
      (nn/delete *conn* node))))
