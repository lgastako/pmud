(ns pmud.users
  (:require [crypto.password.bcrypt :as pw]
            [pmud.db :as db :refer [*conn*]]))

(def bcryptify pw/encrypt)

(def valid-pw? pw/check)

(defn create [un pw]
  (db/create-node {:type :user
                   :username un
                   :pw_hash (bcryptify pw)}))
