(ns buzzlabs-challenge-3.server
    (:require [io.pedestal.connector :as conn]
            [io.pedestal.http.cors :as cors]
            [io.pedestal.http.http-kit :as hk]
            [datomic.api :as d]
            [buzzlabs-challenge-3.database :as bcd]))

(bcd/init-database)

(def conn (d/connect bcd/db-uri))

(defn counter-handler
  [request]
  (let [db (d/db conn)]
    {:status 200 :body (str (bcd/get-counter db))}))

(defn counter-inc-handler
  [request]
  (let [db (d/db conn)
        current_count (bcd/inc-counter db conn)]
    {:status 200 :body (str "Counter successfully incremented\n")}))

(defn counter-dec-handler
  [request]
  (let [db (d/db conn)
        current_count (bcd/dec-counter db conn)]
    {:status 200 :body (str "Counter successfully decremented\n")}))

(defn counter-reset-handler
  [request]
  (let [db (d/db conn)
        current_count (bcd/reset-counter db conn)]
    {:status 200 :body (str "Counter successfully reset\n")}))

(def routes
    #{["/counter" :get counter-handler]
      ["/counter/inc" :post counter-inc-handler]
      ["/counter/dec" :post counter-dec-handler]
      ["/counter/reset" :post counter-reset-handler]})

(defn create-connector []
  (-> (conn/default-connector-map 8080)
      (conn/with-default-interceptors {:allowed-origins (list "*" "http://localhost:9000")})
      (conn/with-routes routes)
      (hk/create-connector nil))) 

(defn main []
  (conn/start! (create-connector)))