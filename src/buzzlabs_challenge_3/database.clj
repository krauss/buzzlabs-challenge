(ns buzzlabs-challenge-3.database
    (:require [datomic.api :as d]))

; Creates the database, only if it does not yet exists
(defn create-db [db-uri]
    (if (d/create-database db-uri)
        (println "- Created database named 'counter'")
        (println "- Database 'counter' already exists")))

; Defines the Datomic's schema
(def counter-schema [{:db/ident :counter/name
                    :db/valueType :db.type/string
                    :db/unique :db.unique/identity
                    :db/cardinality :db.cardinality/one
                    :db/doc "The name of the counter"}

                   {:db/ident :counter/value
                    :db/valueType :db.type/long
                    :db/cardinality :db.cardinality/one
                    :db/doc "The counter's value"}])

; Create the Datomic's schema
(defn create-schema [db conn]
    (if (d/attribute db :counter/name)
        (println "- Database schema already exists")
        (println "- Database schema created: " (str (d/transact conn counter-schema)))))

; Defines the counter
(def counter [{:counter/name "C1" :counter/value 0}])

; Creates the counter in the database
(defn create-counter [db conn]
    (if (d/q '[:find ?id . :where [?id :counter/name "C1"]] db)
        (println "- Counter C1 already exists")
        (println "- Counter C1 created: " (str (d/transact conn counter)))))


(defn get-counter [db]
  (d/q '[:find ?val . :where [?e :counter/name "C1"] [?e :counter/value ?val]] db))

; Function that increments the counter by 1
(defn inc-counter [db conn]
  (let [counter_id (d/q '[:find ?id . :where [?id :counter/name "C1"]] db)
        current_counter_value (d/q '[:find ?val . :where [?e :counter/name "C1"] [?e :counter/value ?val]] db)
        next_value (inc current_counter_value)]
    [(d/transact conn [{:db/id counter_id :counter/value next_value}])]))

; Function that decrements the counter by 1
(defn dec-counter [db conn]
  (let [counter_id (d/q '[:find ?id . :where [?id :counter/name "C1"]] db)
        current_counter_value (d/q '[:find ?val . :where [?e :counter/name "C1"] [?e :counter/value ?val]] db)
        next_value (dec current_counter_value)]
    [(d/transact conn [{:db/id counter_id :counter/value next_value}])]))

; Function that resets the counter
(defn reset-counter [db conn]
  (let [counter_id (d/q '[:find ?id . :where [?id :counter/name "C1"]] db)]
    [(d/transact conn [{:db/id counter_id :counter/value 0}])]))


(def db-uri "datomic:dev://localhost:4334/counter")

(defn get-db [db-connection]
    (d/db db-connection))

; main function
(defn init-database []
    (do 
        (create-db db-uri)
        (let [conn (d/connect db-uri)]
            (create-schema (get-db conn) conn)
            (create-counter (get-db conn) conn))
        (println "- DATABASE SETUP FINISHED!")))