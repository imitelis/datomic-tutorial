(ns datomic-tutorial.core
  (:gen-class))

(require '[datomic.api :as d])

(def db-uri "datomic:dev://localhost:4334/hello")

(d/create-database db-uri)
(def conn (d/connect db-uri))


(def schema-tx
  [{:db/ident       :person/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "A person's name"
    :db.install/_attribute :db.part/db}])

@(d/transact conn schema-tx)

@(d/transact conn [{:db/id #db/id[:db.part/user]
                    :person/name "Alice"}])

(d/q '[:find ?e ?n
       :where [?e :person/name ?n]]
     (d/db conn))

(def person-schema
  [{:db/ident       :person/id
    :db/valueType   :db.type/string
    :db/unique      :db.unique/identity
    :db/cardinality :db.cardinality/one
    :db/doc         "Unique person ID"
    :db.install/_attribute :db.part/db}

   {:db/ident       :person/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Person's name"
    :db.install/_attribute :db.part/db}])

@(d/transact conn person-schema)

@(d/transact conn [{:person/id "person-001"
                    :person/name "Alice"}])

@(d/transact conn [{:db/id [:person/id "person-001"]
                    :person/name "Alice Cooper"}])

(d/q '[:find ?n
       :where
       [?e :person/id "person-001"]
       [?e :person/name ?n]]
     (d/db conn))
;; => #{["Alice Cooper"]}

(def history-db (d/history (d/db conn)))

(d/q '[:find ?name ?op ?tx
       :in $ ?id
       :where
       [?e :person/id ?id]
       [?e :person/name ?name ?tx ?op]]
     history-db "person-001")

(defn tx->inst [conn tx]
  (:db/txInstant (d/entity (d/db conn) tx)))

(tx->inst conn 1002)
;; => #inst "2025-08-05T19:13:42.000-00:00"

