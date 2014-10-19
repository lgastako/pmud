(ns pmud.db-test
  (:require [clojure.test :refer :all]
            [its.log :as log]
            [pmud.db :as db]))

(deftest create-and-remove-node []
  (db/connect)
  (let [type "test-create-and-remove-node"
        n1 (db/create-node {:type type :foo "bar"})
        id1 (:id n1)
        n2 (db/get-node id1)
        id2 (:id n2)]
    (is (= id1 id2 ))
    (is (= "bar" (:foo (:data n1))))
    (is (= "bar" (:foo (:data n2))))
    (db/remove-node type id1)
    (is (nil? (db/get-node id1)))))

(run-tests)


