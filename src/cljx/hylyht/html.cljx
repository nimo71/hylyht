(ns hylyht.html
  (:require [hylyht.markup :as markup]
            [clojure.set :as cset]))

(def global-attributes #{:id :class})

(defn p [content]
  (assert (string? content))
  (markup/element :p {} content))

(def allowed-form-attributes
  (cset/union global-attributes
    #{:accept :accept-charset :action :autocomplete
      :enctype :method :name :novalidate :target}))

(defn allowed-form-attributes? [attr]
  (cset/subset? (keys attr) allowed-form-attributes))

(def allowed-form-children
  #{:input :textarea :button :select :option :optgroup :fieldset :label})

(defn allowed-form-child? [child]
  (or (string? child)
      (contains? allowed-form-children (first child))))

(defn allowed-form-children? [children]
  (reduce #(and %1 (allowed-form-child? %2)) true children))

(defn form [& attr-children]
  (let [el (apply markup/element :form attr-children)
        [_ attrs & children] el]
    (assert (allowed-form-attributes? attrs))
    (assert (apply allowed-form-children? children))
    el))

(defn input [& attr]
  (apply markup/element :input attr))
