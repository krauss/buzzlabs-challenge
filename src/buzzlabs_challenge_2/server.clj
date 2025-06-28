(ns buzzlabs-challenge-2.server
    (:require [io.pedestal.connector :as conn]
            [io.pedestal.http.cors :as cors]
            [io.pedestal.http.http-kit :as hk]))

(def click-counter (atom 0))

(defn counter-handler
  [request]
  {:status 200 :body (str @click-counter)})

(defn counter-inc-handler
  [request]
  (let [current_count (swap! click-counter inc)]
    {:status 200 :body (str current_count)}))

(defn counter-dec-handler
  [request]
  (let [current_count (swap! click-counter dec)]
    {:status 200 :body (str current_count)}))

(defn counter-reset-handler
  [request]
  (let [current_count (reset! click-counter 0)]
    {:status 200 :body (str current_count)}))

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