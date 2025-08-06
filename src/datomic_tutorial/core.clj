(ns datomic-tutorial.core
  (:gen-class))

(require '[datomic.api :as d])

(def db-uri "datomic:dev://localhost:4334/hello")

(d/create-database db-uri)

(def conn (d/connect db-uri))