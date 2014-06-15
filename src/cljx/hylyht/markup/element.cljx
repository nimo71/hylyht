(ns hylyht.markup.element
  (:require [hylyht.markup :as markup])
  (:import [hylyht.markup Markup]))

(defn ^:private create-open-tag [tagname attributes]
  (let [attrs (if (> (count attributes) 0)
                (str " " (markup/attr-str (reverse attributes)))
                "")]
    (str "<" tagname attrs ">")))

(defrecord Element [tag-name attributes children]
  markup/Markup
  (markup-str [_]
    (str (create-open-tag (name tag-name) attributes)
         (markup/markup-str children)
         (str "</" (name tag-name) ">"))))

(defn element [tag-name & attrs-children]
    (let [separate (apply markup/separate-attrs-and-children attrs-children)
        attrs (:attr separate)
        children (:children separate)]
      (Element. tag-name attrs children)))

;;TODO: content should be optional, if no content could take the form <name attr="value" .../>
