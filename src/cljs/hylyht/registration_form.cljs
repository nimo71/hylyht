(ns hylyht.registration-form
  (:require [hylyht.html :refer [a form input label]]))

(defn create []
  [(a :href "./app.html#login" "Login")
   (form :id "registration_form" :method "post" :action "/register"
     (label :for "username" "Username: ")
     (input :type "text" :id "username" :name "username")

     (label :for "confirm_username" "Confirm username: ")
     (input :type "text" :id "confirm_username" :name "confirm_username")

     (label :for "password" "Password: ")
     (input :type "text" :id "password" :name "password")

     (label :for "confirm_password" "Confirm password: ")
     (input :type "text" :id "confirm_password" :name "confirm_password")

     (input :type "submit" :id "submit_registration" :value "Register"))])

(defn init []
  )
