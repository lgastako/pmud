(ns pmud.web.autoexec
  (:require [its.log :as log]
            [om.core :as om :include-macros true]
            [pmud.web.views :refer [main-view]]
            [schema.core :as s :include-macros true]))

(log/set-level! :debug)
(log/debug :pmud.web.autoexec :begin)


(def HistoryEntry
  {:description s/Str
   s/Any s/Any})

(def Application
  {:username (s/maybe s/Str)
   :input (s/maybe s/Str)
   :history [HistoryEntry]})

(defn make-app []
  (atom (s/validate Application {:username nil
                                 :input nil
                                 :history [{:description "Welcome to pmud."}]})))

(defn init []
  (let [app (atom (make-app))]
    (log/debug :init {:app app})
    (om/root main-view app {:target js/document.body})
    (let [minibuffer (js/document.getElementById "minibuffer")]
      (.focus minibuffer))))

(init)

(log/debug :pmud.web.autoexec :end)
