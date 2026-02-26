(ns datomic-tutorial.schemas.movie
  (:require [datomic-tutorial.core :refer [conn]]
            [datomic.api :as d]))

(def movie-schema
  [{:db/ident       :movie/id
    :db/valueType   :db.type/string
    :db/unique      :db.unique/identity
    :db/cardinality :db.cardinality/one
    :db/doc         "Unique movie ID"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/title
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Movie title"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/year
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Release year of the movie"
    :db.install/_attribute :db.part/db}])

@(d/transact conn movie-schema)

@(d/transact conn
             [{:movie/id "movie-001"
               :movie/title "The Terminator"
               :movie/year 1984}

              {:movie/id "movie-002"
               :movie/title "The Shining"
               :movie/year 1980}

              {:movie/id "movie-003"
               :movie/title "Alien"
               :movie/year 1979}])

(d/q '[:find ?title
       :where
       [?m :movie/title ?title]
       [?m :movie/year ?year]
       [(< ?year 1984)]]
     (d/db conn))

(d/q '[:find ?title
       :where
       [?m :movie/title ?title]
       [?m :movie/year ?year]
       [(< ?year 1984)]
       [(java.lang.String/startsWith ?title "The")]]
     (d/db conn))

(def updated-movie-schema
  [{:db/ident       :movie/id
    :db/valueType   :db.type/string
    :db/unique      :db.unique/identity
    :db/cardinality :db.cardinality/one
    :db/doc         "Unique movie ID"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/title
    :db/valueType   :db.type/string
    :db/index       true
    :db/cardinality :db.cardinality/one
    :db/doc         "Movie title"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/year
    :db/valueType   :db.type/long
    :db/index       true
    :db/cardinality :db.cardinality/one
    :db/doc         "Release year"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/cast
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/doc         "Actors in the movie"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/director
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Director of the movie"
    :db.install/_attribute :db.part/db}

   {:db/ident       :movie/box-office
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Box office earnings in USD"
    :db.install/_attribute :db.part/db}])

(d/transact conn updated-movie-schema)