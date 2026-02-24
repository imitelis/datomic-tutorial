(ns datomic-tutorial.queries.05-predicates
  (:require [datomic.api :as d]
            [datomic-tutorial.core :refer [conn]]))

;; Find all movies released before 1984
(d/q '[:find ?title
       :where
       [?m :movie/title ?title]
       [?m :movie/year ?year]
       [(< ?year 1984)]]
     (d/db conn))

;; Java method as a predicate function:
(d/q '[:find ?title
       :where
       [?m :movie/title ?title]
       [?m :movie/year ?year]
       [(< ?year 1984)]
       [(java.lang.String/startsWith ?title "The")]]
     (d/db conn))