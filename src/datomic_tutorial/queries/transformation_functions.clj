(ns datomic-tutorial.queries.transformation-functions
  (:require [datomic.api :as d]
            [datomic-tutorial.core :refer [conn]]))

;; Calculate the (very approximate) age of a person:
(defn age [birthday today]
  (quot (- (.getTime today)
           (.getTime birthday))
        (* 1000 60 60 24 365)))

;; Calculate the age of a person inside the query itself:
(d/q '[:find ?age
       :in $ ?name ?today
       :where
       [?p :person/name ?name]
       [?p :person/born ?born]
       [(age ?born ?today) ?age]]
     (d/db conn))

;; Transformation functions can't be nested. You can't write
;; [(f (g ?x)) ?a]

;; Bind intermediate results in temporary pattern variables
;; [(g ?x) ?t]
;; [(f ?t) ?a]