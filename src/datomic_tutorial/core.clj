(ns datomic-tutorial.core
  (:require '[datomic.api :as d]))

;; store database uri
(use '[datomic.api :only [q db] :as d])

;; create database
(def uri "datomic:dev://localhost:4334/hello")


;; connect to database
(def conn (d/connect uri))

;; display first statement