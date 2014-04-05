(ns hylyht.html
  (:require [hylyht.markup :refer [element]]
            [clojure.set :refer [subset? union]]))

(defn p [content]
  (assert (string? content))
  (element :p {} content))

(def global-attributes #{:id :class})

(def allowed-form-attributes
  (union global-attributes
    #{:accept :accept-charset :action :autocomplete
      :enctype :method :name :novalidate :target}))

(defn allowed-form-attributes? [attr]
  (subset? (keys attr) allowed-form-attributes))

(def allowed-form-children
  #{:input :textarea :button :select :option :optgroup :fieldset :label})

(defn allowed-form-child? [child]
  (or (string? child)
      (contains? allowed-form-children (first child))))

(defn allowed-form-children? [children]
  (reduce #(and %1 (allowed-form-child? %2)) true children))

(defn form [& attr-children]
  (let [el (apply element :form attr-children)
        attrs (second el)
        children (nthrest el 2)]
    (assert (allowed-form-attributes? attrs))
    (assert (apply allowed-form-children? children))
    el))

(defn input [& attr]
  (apply element :input attr))
