(ns pmud.web.autoexec
  (:require [its.log :as log]
            [om.core :as om :include-macros true]
            [pmud.web.views :refer [main-view]]
            [schema.core :as s :include-macros true]))

(log/set-level! :debug)
(log/debug :pmud.web.autoexec :begin)

(def Application
  {:username (s/maybe s/Str)
   :input (s/maybe s/Str)})

(defn make-app []
  (atom (s/validate Application {:username nil
                                 :input nil})))

(defn init []
  (let [app (atom (make-app))]
    (om/root main-view app {:target js/document.body})))

(init)

(log/debug :pmud.web.autoexec :end)
