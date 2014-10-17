(ns user
  "Tools for interactive development with the REPL. This file should
   not be included in a production build of the application.

   See:
   https://github.com/stuartsierra/reloaded
   http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded
  "
  (:require [clojure.java.io :as io]
            [clojure.java.javadoc :refer [javadoc]]
            [clojure.pprint :refer [pprint]]
            [clojure.reflect :refer [reflect]]
            [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.test :as test]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [its.log :as log]
            [pmud.web.server :as server]))

(def system
  "A Var containing an object representing the application under development."
  nil)

(defn init
  "Creates and initializes the system under development in the atom #'system."
  []
  (def system {:state :initialized}))

(defn start
  "Starts the system running, updates the atom #'system."
  []
  (def system
    (merge system
           {:killswitch (server/serve 1999)}
           {:state :started})))

(defn stop
  "Stops the system if it is currently running, updates the atom #'system."
  []
  (log/debug :stopping {:system system})
  (when-let [killswitch (:killswitch system)]
    (killswitch)))

(defn go
  "Initializes and starts the system running."
  []
  (init)
  (start)
  :ready)

(defn reset
  "Stops the system, reloads modified source files, and restarts it."
  []
  (stop)
  (refresh :after 'user/go))
