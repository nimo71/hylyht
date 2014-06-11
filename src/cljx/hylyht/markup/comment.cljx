(ns hylyht.markup.comment
  (:require [hylyht.markup :as markup]))

(defrecord Comment [comment]
  markup/Markup
  (markup-str [_]
    (str "<!-- " comment " -->")))

(defn <!-- [comment]
  (Comment. comment))
