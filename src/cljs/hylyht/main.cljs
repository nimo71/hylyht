(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [element element-str]]))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

(defn build-content []
  (let [content (element "p" {} "Hello hylyht!")]
    (attribute-str content)))
