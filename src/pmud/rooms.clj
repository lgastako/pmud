(ns pmud.rooms
  (:require [pmud.db :as db]))

(defn create [name & props]
  (let [entity {:type :room
                :name name}]
    (db/create-node (into entity props))))

(defn destroy [id]
  (db/remove-node :room id))
