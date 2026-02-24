(ns datomic-tutorial.queries.basic-queries
  (:require [datomic-tutorial.core :refer [conn]]))
  
(require '[datomic.api :as d])

;; Datoms look like
;; [<e-id>  <attribute>      <value>          <tx-id>]
;; ...
;; [ 167    :person/name     "James Cameron"    102  ]
;; [ 234    :movie/title     "Die Hard"         102  ]
;; [ 234    :movie/year      1987               102  ]
;; [ 235    :movie/title     "Terminator"       102  ]
;; [ 235    :movie/director  167                102  ]
;; ...

;; Basic query
(d/q '[:find ?e
       :where [?e :person/name "Ridley Scott"]]
     (d/db conn))

;; Same query without trailing
(d/q '[:find ?e
       :where [?e :person/name "Ridley Scott" _]]
     (d/db conn))