(ns pmud.parser-test
  (:require-macros [cemerick.cljs.test :refer [is deftest run-tests]])
  (:require [cemerick.cljs.test :as t]))

(deftest somewhat-less-wat
  (is (= "{}[]" (+ {} []))))

