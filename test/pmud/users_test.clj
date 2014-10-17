(ns pmud.users-test
  (:require [clojure.test :refer :all]
            [pmud.users :refer :all]))

(deftest test-bcryptify-and-valid-pw?
  (let [secret1 "secret"
        secret2 "secret2"
        wrong "wrong"
        encrypted1 (bcryptify secret1)
        encrypted2 (bcryptify secret2)]
    (is (not= encrypted1 secret1))
    (is (valid-pw? secret1 encrypted1))
    (is (not (valid-pw? wrong encrypted1)))
    (is (not= encrypted2 secret2))
    (is (valid-pw? secret2 encrypted2))
    (is (not (valid-pw? wrong encrypted2)))
    (is (not= encrypted1 encrypted2))
    (is (not (valid-pw? secret1 encrypted2)))
    (is (not (valid-pw? secret2 encrypted1)))))

