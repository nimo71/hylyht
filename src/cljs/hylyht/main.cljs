(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [element-str]]
            [hylyht.html :refer [p]]))

(defn ^:export init []
  (append! (by-id "content")
           (build-content)))

;;TODO: add html render function to traverse the markup and stringify
(defn build-content []
  (let [content (p "Hello hylyht!")]
    (element-str content)))
