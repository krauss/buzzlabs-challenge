(ns buzzlabs-challenge-3.core
    (:require [reagent.core :as r] 
            [reagent.dom :as rdom]
            [ajax.core :refer [GET POST]]))

(def state (r/atom {:counter 0}))

(defn inc-counter [state]
    (POST "http://localhost:8080/counter/inc"
        {:body ""
        :response-format :text
        :handler nil
        :error-handler (fn [details] (.warn js/console (str "Failed to increment counter: " details)))}))

(defn dec-counter [state]
    (POST "http://localhost:8080/counter/dec"
        {:body ""
        :response-format :text
        :handler nil
        :error-handler (fn [details] (.warn js/console (str "Failed to decrement counter: " details)))}))

(defn reset-counter [state]
    (POST "http://localhost:8080/counter/reset"
        {:body ""
        :response-format :text
        :handler nil
        :error-handler (fn [details] (.warn js/console (str "Failed to reset counter: " details)))}))

(defn get-counter [state]
    (GET "http://localhost:8080/counter"
        {:handler (fn [response] (swap! state assoc :counter response))
        :error-handler (fn [details] (.warn js/console (str "Failed to get counter: " details)))
        :response-format :text}))

(get-counter state)

(add-watch state nil (partial get-counter state))

(defn counting-app-2 []
  [:div
    [:div {:style {
                :width "auto" 
                :margin "15px" 
                :padding "15px 30px 15px 30px" 
                :border-radius "20px" 
                :background "#F0F0F0" 
                :box-shadow "20px 20px 60px #E0E0E0 -20px -20px 60px #ffffff" 
                :position "fixed" 
                :top "50%" 
                :left "50%" 
                :transform "translate(-50%, -50%)"}}
        [:div {:style {:display "flex" :align-items "center"}}
            [:div
                [:div {:style {:display "flex" :align-items "center"}}
                    [:h1 "BuzzLabs Challenge 3 "]
                    [:img {:id "icon" :src "icon.svg" :style {:padding "15px 30px 15px 30px" :width "35px" :height "35px"}}]
                ]
                [:hr ]
                [:div {:style {:display "flex" :align-items "center"}}
                    [:div
                        [:h3 {:style {:display "flex" :align-items "center"}} "Counter saves state in database"] 
                        [:div
                            [:h1 (get (deref state) :counter)]
                        ]
                        [:div
                            [:input {:type "button" :value "-" :on-click #(dec-counter state) :style {:font-size "18px" :font-weight "bold"}}]
                            [:input {:type "button" :value "Reset" :on-click #(reset-counter state) :style {:font-size "16px" :font-weight "bold"}}]
                            [:input {:type "button" :value "+" :on-click #(inc-counter state) :style {:font-size "18px" :font-weight "bold"}}]
                        ]
                    ]
                ]
            ]
        ]
    ]    
    ])

; Start the app
(rdom/render [counting-app-2] (js/document.getElementById "app"))