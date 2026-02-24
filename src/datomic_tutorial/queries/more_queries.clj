(ns datomic-tutorial.queries.more-queries 
  (:require [datomic.api :as d]
            [datomic-tutorial.core :refer [conn]]))

;; A datom is a 4-tuple [eid attr val tx]


;; Attributes

;; We know for certain that :person/name is one such attribute
(d/q '[:find ?attr
       :where
       [?p :person/name]
       [?p ?attr]]
     (d/db conn))

;; Find all attributes that are associated with person entities in our database
(d/q '[:find ?attr
       :where
       [?p :person/name]
       [?p ?attr]]
     (d/db conn))

;; This is because attributes are also entities in our database!


;; Transactions

;; It's also possible to run queries to find information about transactions
;; The transaction entity is the fourth element in the datom vector
(d/q '[:find ?timestamp 
       :where
       [?p :person/name "James Cameron" ?tx]
       [?tx :db/txInstant ?timestamp]] 
     (d/db conn))