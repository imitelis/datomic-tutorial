(ns datomic-tutorial.queries.03-parametrized-queries
  (:require [datomic.api :as d]
            [datomic-tutorial.core :refer [conn]]))

;; Original query
(d/q '[:find ?title 
       :where 
       [?p :person/name "Sylvester Stallone"] 
       [?m :movie/cast ?p] 
       [?m :movie/title ?title]]
     (d/db conn))

;; Parametrized query
(def query '[:find ?title
             :in $ ?name
             :where
             [?p :person/name ?name]
             [?m :movie/cast ?p]
             [?m :movie/title ?title]])

;; The above query is executed like
(d/q query (d/db conn) "Sylvester Stallone")

;; Each data pattern is actually a 5-tuple (EAV data model extended)
;; [<database> <entity-id> <attribute> <value> <transaction-id>]
;; It's just that the database part is implicit


;; Tuple query
(d/q '[:find ?title
       :in $ [?director ?actor]
       :where
       [?d :person/name ?director]
       [?a :person/name ?actor]
       [?m :movie/director ?d]
       [?m :movie/cast ?a]
       [?m :movie/title ?title]]
     (d/db conn))

;; Instead of two distinct inputs
;; :in $ ?director ?actor


;; Collection query
(d/q '[:find ?title
       :in $ [?director ...]
       :where
       [?p :person/name ?director]
       [?m :movie/director ?p]
       [?m :movie/title ?title]]
     (d/db conn))

;; To find all movies directed by either one director or the other
;; ?director


;; Relational query

;; Consider a relation with tuples [movie-title box-office-earnings]:
;; [
;; ...
;; ["Die Hard" 140700000]
;; ["Alien" 104931801]
;; ["Lethal Weapon" 120207127]
;; ["Commando" 57491000]
;; ...
;; ]

;; Find box office earnings for a particular director 
(d/q '[:find ?title ?box-office
       :in $ ?director [[?title ?box-office]]
       :where
       [?p :person/name ?director]
       [?m :movie/director ?p]
       [?m :movie/title ?title]]
     (d/db conn))