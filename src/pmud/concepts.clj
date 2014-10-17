(ns pmud.concepts
  (:require [pmud.db :as db]))

(defn create [label & props]
  (let [entity {:type :concept
                :label label}]
    (db/create-node (into entity props))))
