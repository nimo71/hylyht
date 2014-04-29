(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [markup-str]]
            [hylyht.registration-form :as reg-form]))

(defn build-content []
  (apply markup-str (reg-form/create)))

(defn ^:export init []
  (append! (by-id "content")
           (build-content))

  (reg-form/init))

;TODO:
;  * listen for navigation events and load the correct page...
;  * remove domina and use google clojure instead, wrap clojure...
;  * add html render function to traverse the markup and stringify
;  * create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
