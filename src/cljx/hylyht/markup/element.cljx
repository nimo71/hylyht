(ns hylyht.markup.element
  (:require [hylyht.markup :as markup])
  (:import [hylyht.markup Markup]))

(defn ^:private create-open-tag [tagname attributes]
  (let [attrs (if (> (count attributes) 0)
                (str " " (markup/attr-str (reverse attributes)))
                "")]
    (str "<" tagname attrs ">")))


; TODO: All of the types need to be valid for cljs as well as clj - can String work for cljs??
(extend-protocol markup/Markup
  String
  (markup-str [this] this)

  clojure.lang.ISeq
  (markup-str [this]
    (reduce #(str %1 (markup/markup-str %2)) "" this)))

(defrecord Element [tag-name attributes children]
  markup/Markup
  (markup-str [_]
    (str (create-open-tag tag-name attributes)
         (markup/markup-str children)
         (str "</" tag-name ">"))))

(defn element [tag-name attributes & children]
  (Element. tag-name attributes children))

;;TODO: content should be optional, if no content could take the form <name attr="value" .../>
