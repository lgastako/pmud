(ns pmud.web.views
  (:require [dommy.core :as d :include-macros true]
            [om.core :as om :include-macros true]
            [its.log :as log]
            [pmud.web :as web]))


(defn history-entry-view [entry owner]
  (web/view [:li.entry [:p (:description entry)]]))

(defn enter-key? [evt]
  (= 13 (.-charCode evt)))

(defn submit-command [event]
  (log/debug :submit-command {:event event})
  (let [el (.target event)]
    (log/debug :submit-command {:el el
                                :innerHTML (.-innerHTML el)})))

(defn main-view [app owner]
  (web/view [:div.main
             [:div.terminal
              [:ol.history (om/build-all history-entry-view (:history app))]
              [:span.prompt "> "]
              [:textarea#minibuffer {:autofocus :true
                                     :on-key-press #(when (enter-key? %) (submit-command %))}]]]))
