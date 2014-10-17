(ns pmud.relations
  (:require [clojurewerkz.neocons.rest.relationships :as nrl]))

(defn create [rel-type src dst & props]
  (apply nrl/create src dst rel-type props))
