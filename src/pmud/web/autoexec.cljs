(ns pmud.web.autoexec
  (:require [om.core :as om :include-macros true]
            [pmud.web.views :refer [main-view]]
            [schema.core :as s :include-macros true]))

(def Application
  {:username (s/maybe s/Str)})

(defn make-app []
  (atom (s/validate Application {:username nil})))

(defn init []
  (let [app (atom (make-app))]
    (om/root main-view app {:target js/document.body})))
