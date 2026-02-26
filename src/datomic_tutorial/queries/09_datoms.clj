(ns datomic-tutorial.queries.09-datoms
  (:require [datomic.api :as d]
            [datomic-tutorial.core :refer [conn]]))

;; Datoms query
(d/datoms (d/db conn) :eavt)

(d/datoms (d/db conn) :avet :movie/title)