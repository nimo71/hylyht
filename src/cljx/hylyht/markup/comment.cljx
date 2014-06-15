(ns hylyht.markup.comment
  (:require [hylyht.markup :as markup]))

(defrecord Comment [content]
  markup/Markup
  (markup-str [_]
    (str "<!-- " content " -->")))

(defn <!-- [& content]
  (Comment. (apply markup/markup-str content)))
