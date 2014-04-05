(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [element-str]]
            [hylyht.html :refer [p form input]]))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

;;TODO: add html render function to traverse the markup and stringify
(defn build-content []
  (element-str (login-form)))

(defn login-form []
  (form :id "login_form" :method "post" :action "/login"
    "Username: "
    (input :type "text" :id "username")
    "Password: "
    (input :type "text" :id "password")))
