(ns datomic-tutorial.schema
  (:require '[datomic.api :as d]))

(def schema [{:db/ident       :person/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :person/age
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])

(d/transact conn schema)