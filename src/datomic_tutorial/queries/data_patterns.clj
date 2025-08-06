(ns datomic-tutorial.queries.data-patterns
  (:require [datomic-tutorial.core :refer [conn]]))

(require '[datomic.api :as d])

;; Data query
;; [:find ?title
;;  :where
;;  [?e :movie/year 1987]
;;  [?e :movie/title ?title]]

;; Order does not matter for query
;; [:find ?title
;;  :where
;;  [?e :movie/title ?title]]
;;  [?e :movie/year 1987]



;; [?m :movie/title "Lethal Weapon"]

;; [?m :movie/cast ?p]

;; [?p :person/name ?name]

;; [:find ?name
;;  :where
;;  [?m :movie/title "Lethal Weapon"]
;;  [?m :movie/cast ?p]
;;  [?p :person/name ?name]]