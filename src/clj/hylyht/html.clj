(ns hylyht.html
  (:require [hylyht.markup :refer [el]]
            [clojure.set :refer [subset?]]))

(defn p [content]
  (assert (string? content))
  (el :p {} content))

(def allowed-form-attributes
  #{:accept :accept-charset :action :autocomplete
    :enctype :method :name :novalidate :target})

(def allowed-form-children
  #{:input :textarea :button :select :option :optgroup :fieldset :label})

(defn allowed-form-attributes? [attr]
  (subset? (keys attr) allowed-form-attributes))

(defn allowed-form-children? [children]
  (subset? (reduce #(conj %1 (first %2)) #{} children) allowed-form-children))

(defn form [attr & children]
  (assert (allowed-form-attributes? attr))
  (assert (allowed-form-children? children))
  (apply el :form attr children))

(defn input [attr]
  (el :input attr))
