(ns pmud.web.views
  (:require [dommy.core :as d :include-macros true]
            [goog.string :as gstring]
            [om.core :as om :include-macros true]
            [its.log :as log]
            [pmud.web :as web]))


(defn now [] (js/Date.))

(defn enter-key? [evt]
  (= 13 (.-charCode evt)))

(defn clear-value! [el]
  ;; Not sure why this is necessary, but it works.
  (set! (.-value el) " ")
  (set! (.-value el) ""))

(defn history-entry-view [entry owner]
  (log/debug :history-entry-view {:entry entry})
  (let [{ts :timestamp d :description} entry]
    (log/debug :hev-dbg {:ts ts :desc d :entry entry})
    (web/view [:li.entry [:p [:span.timestamp (.toJSON ts)] d]])))

(defn execute-command [app cmd]
  (log/debug :execute-command {:cmd cmd}))

(defn mk-hist [t d]
  {:type t
   :description d
   :timestamp (now)})

(defn submit-command [app event]
  (let [el (.-target event)
        v (.-value el)
        cmd-entry (mk-hist :cmd (str "User " (gstring/unescapeEntities "&rArr;") " " v))]
    (clear-value! el)
    (log/debug :submit-command {:el el :v v})
    (om/transact! app [:history] #(cons cmd-entry %))
    (log/debug :updated {:app @app})
    (execute-command app v)
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
