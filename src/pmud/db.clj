(ns pmud.db
  (:require [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.nodes :as nn]
            [its.log :as log]))

(def DEFAULT_URL "http://127.0.0.1:7474/db/data/")

(def ^:dynamic *conn*)

(defn connect
  ([] (connect DEFAULT_URL))
  ([url] (def ^:dynamic *conn* (nr/connect url))))

(defn create-node [props]
  (nn/create *conn* props))

(defn get-node [id]
  (try
    (nn/get *conn* id)
    (catch Exception ex
      (let [status (:status (:object (.getData ex)))]
        (when (not= 404 status)
          (throw ex))))))

(defn remove-node [type id]
  (let [node (nn/get *conn* id)]
    (when (= type (:type (:data node)))
      (nn/delete *conn* node))))
