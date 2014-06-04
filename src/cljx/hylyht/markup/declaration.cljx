(ns hylyht.markup.declaration
  (:require [hylyht.markup :as markup]))

(defn declaration [& decs]
  (let [decl (apply markup/declaration decs)]
    (reify markup/Markup
      (markup-str [_]
         (markup/declaration-str decl)))))


