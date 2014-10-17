(ns pmud.web.views
  (:require [om.core :as om :include-macros true]
            [pmud.web :refer [view]]))

(defn main-view [app owner]
  (view [:div.main]))

