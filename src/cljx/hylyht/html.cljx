(ns hylyht.html
  (:require [hylyht.markup :refer [element]]
            [clojure.set :refer [union subset?]]))

;;TODO: Consider namespace for each element?
;;TODO: Should children be restricted? Maybe have strict mode or warning?
;;TODO: Should be able to parse html and generate markup components

(def global-attributes #{:id :class})

(defn p [content]
  (assert (string? content))
  (element :p {} content))

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
      (contains? allowed-form-children (second child))))

(defn allowed-form-children? [children]
  (reduce #(and %1 (allowed-form-child? %2)) true children))

(defn form [& attr-children]
  (let [el (apply element :form attr-children)
        [_ _ attrs & children] el]
    (assert (allowed-form-attributes? attrs))
    (assert (apply allowed-form-children? children))
    el))

(defn input [& attr]
  (apply element :input attr))

(defn label [& attr-children]
  (apply element :label attr-children))

(defn html [& children]
  (apply element :html children))

(defn head [& children]
  (apply element :head children))

(defn body [& children]
  (apply element :body children))
