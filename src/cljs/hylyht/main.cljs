(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [markup-str]]
            [hylyht.registration-form :refer [create-registration-form]]))

(defn build-content []
  (markup-str (create-registration-form)))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

;TODO:
;  add html render function to traverse the markup and stringify
;  create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
