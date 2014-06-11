(ns hylyht.markup.declaration
  (:require [hylyht.markup :as markup]))

(defrecord Declaration [decs]
  markup/Markup
  (markup-str [_]
    (str "<" (subs (reduce #(str %1 " " %2) "" decs) 1) ">")))

(defn declaration [& decs]
  (Declaration. decs))
