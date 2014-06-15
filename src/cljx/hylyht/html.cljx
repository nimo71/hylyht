(ns hylyht.html
  (:refer-clojure :exclude [meta])
  (:require [hylyht.markup :as markup]
            [hylyht.markup.declaration :refer [declaration]]
            [hylyht.markup.element :refer [element]]
            [hylyht.markup.comment :as cmt]))

(defn doctype [& declarations]
  (apply declaration "!DOCTYPE" declarations))

(defn meta [& attr]
  (apply declaration :meta attr))

(defn title [title-str]
  (element :title title-str))

(defn html [& attr-children]
  (apply element :html attr-children))

(defn head [& children]
  (apply element :head children))

(defn <!--
  ([content]
     (cmt/<!-- content))

  ; Conditional comment for IE browsers
  ([condition & children]
    (let [cond-start   (str"[if " condition "]>")
          children-str (markup/markup-str children)
          cond-end     "<![endif]"]
      (cmt/<!-- (str cond-start children-str cond-end)))))

(defn script [& attr]
  (apply element :script attr))

(defn link [& attr]
  (apply element :link attr))

(defn body [& children]
  (apply element :body children))

(defn div [& attr-children]
  (apply element :div attr-children))

(defn p [& attr-children]
  (apply element :p attr-children))

(defn form [& attrs-children]
  (apply element :form attrs-children))

(defn input [& attr]
  (apply element :input attr))

(defn label [& attr-children]
  (apply element :label attr-children))

(defn a [& attr-children]
  (apply element :a attr-children))
