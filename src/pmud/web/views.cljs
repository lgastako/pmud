(ns pmud.web.views
  (:require [dommy.core :as d :include-macros true]
            [om.core :as om :include-macros true]
            [its.log :as log]
            [pmud.web :as web]))


(defn history-entry-view [entry owner]
  (log/debug :history-entry-view {:entry entry})
  (web/view [:li.entry [:p (:description entry)]]))

(defn enter-key? [evt]
  (= 13 (.-charCode evt)))

(defn clear-value! [el]
  ;; Not sure why this is necessary, but it works.
  (set! (.-value el) " ")
  (set! (.-value el) ""))

(defn submit-command [app event]
  (let [el (.-target event)
        v (.-value el)
        cmd-entry {:description (str "USER TYPED: " v)}]
    (clear-value! el)
    (log/debug :submit-command {:el el :v v})
    (om/transact! app [:history] #(cons cmd-entry %))
    (log/debug :updated {:app @app})
    (.preventDefault event)
    false))

(defn main-view [app owner]
  (let [history (:history app)]
    (log/debug :main-view {:history history})
    (log/debug :will-show-n-history-entries (count history))
    (web/view [:div.main
               [:div.terminal
                [:span.prompt "> "]
                [:textarea#minibuffer {:autofocus :true
                                       :on-key-press #(when (enter-key? %) (submit-command app %))}]
                [:ul.history (om/build-all history-entry-view history)]]])))
