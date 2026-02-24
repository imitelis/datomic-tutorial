(ns datomic-tutorial.queries.data-patterns
  (:require [datomic-tutorial.core :refer [conn]]))

(require '[datomic.api :as d])

;; Data patterns in a :where clause:
(d/q '[:find ?title
       :where
       [?e :movie/year 1987]
       [?e :movie/title ?title]]
     (d/db conn))

;; Order does not matter for query
(d/q '[:find ?title
       :where
       [?e :movie/title ?title]
       [?e :movie/year 1987]]
     (d/db conn))

;; Three data patterns together
;; [?m :movie/title "Lethal Weapon"]
;; [?m :movie/cast ?p]
;; [?p :person/name ?name]

;; Final query
(d/q '[:find ?name 
       :where 
       [?m :movie/title "Lethal Weapon"] 
       [?m :movie/cast ?p] 
       [?p :person/name ?name]]
     (d/db conn))