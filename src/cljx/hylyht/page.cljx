(ns hylyht.page
  (:require [hylyht.html :refer [html head body]]))

(defn page []
  [(html
     (head)
     (body))])
