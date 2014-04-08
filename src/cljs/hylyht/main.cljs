(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [markup-str]]
            [hylyht.html :refer [form input label]]))

(defn login-form []
  (form :id "login_form" :method "post" :action "/login"
    (label :for "username" "Username: ")
    (input :type "text" :id "username")
    (label :for "password" "Password: ")
    (input :type "text" :id "password")))

(defn build-content []
  (markup-str (login-form)))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

;TODO:
;  add html render function to traverse the markup and stringify
;  create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
