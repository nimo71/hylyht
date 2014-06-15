(ns hylyht.markup.declaration
  (:require [hylyht.markup :as markup]))

(defrecord Declaration [dec-name attrs]
  markup/Markup
  (markup-str [_]
    (if (keyword? dec-name)
      (str "<" (name dec-name) " " (markup/attr-str (apply hash-map attrs)) ">")
      (str "<" (subs (reduce #(str %1 " " %2) "" (cons dec-name attrs)) 1) ">"))))

(defn declaration [& decs]
    (let [[dec-name & attrs] decs]
      (Declaration. dec-name attrs)))
