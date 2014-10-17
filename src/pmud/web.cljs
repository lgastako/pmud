(ns pmud.web
  (:require [om.core :as om :include-macros true]
            [sablono.core :refer-macros [html]]))

(defn view [el]
  (om/component (html el)))
