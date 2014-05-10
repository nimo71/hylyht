(ns hylyht.login-form
  (:require [hylyht.html :refer [form input label]]))

(defn create []
  [(form :id "login_form" :method "post" :action "/login"
    (label :for "username" "Username: ")
    (input :type "text" :id "username" :name "username")
    (label :for "password" "Password: ")
    (input :type "text" :id "password" :name "password")
    (input :type "submit" :id "submit_login" :value "Login"))])
