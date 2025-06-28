(ns buzzlabs-challenge-1.core
    (:require [reagent.core :as r] 
            [reagent.dom :as rdom]
            ["react" :as react]))


(def click-count (r/atom 0))

(defn counting-app-1 []
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
                    [:h1 "BuzzLabs Challenge 1 "]
                    [:img {:id "icon" :src "icon.svg" :style {:padding "15px 30px 15px 30px" :width "35px" :height "35px"}}]
                ]
                [:hr ]
                [:div {:style {:display "flex" :align-items "center"}}
                    [:div
                        [:h3 {:style {:display "flex" :align-items "center"}} "Counter does not save state"] 
                        [:div
                            [:h1 @click-count]
                        ]
                        [:div
                            [:input {:type "button" :value "-" :on-click #(swap! click-count dec) :style {:font-size "18px" :font-weight "bold"}}]
                            [:input {:type "button" :value "+" :on-click #(swap! click-count inc) :style {:font-size "18px" :font-weight "bold"}}]
                        ]
                        [:div {:style {:display "flex" :align-items "center"}}
                            [:input {:type "button" :value "Reset" :on-click #(reset! click-count 0) :style {:font-size "16px" :font-weight "bold"}}]
                        ]
                    ]
                ]
            ]
        ]
    ]    
    ])

(rdom/render [counting-app-1] (js/document.getElementById "app"))