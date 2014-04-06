(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [element-str]]
            [hylyht.html :refer [form input label]]))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

;TODO:
;  add html render function to traverse the markup and stringify
;  create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
(defn build-content []
  (element-str (login-form)))

(defn login-form []
  (form :id "login_form" :method "post" :action "/login"
    (label :for "username" "Username: ")
    (input :type "text" :id "username")
    (label :for "password" "Password: ")
    (input :type "text" :id "password")))
