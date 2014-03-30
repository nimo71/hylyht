(ns hylyht.html
  (:require [hylyht.markup :refer [el]]))

(defn p [content]
  (assert (string? content))
  (el "p" {} content))
