(ns hylyht.html
  (:require [hylyht.markup :refer [el]]
            [clojure.set :refer [subset? union]]))

(defn p [content]
  (assert (string? content))
  (el :p {} content))

(def global-attributes #{:id :class})

(def allowed-form-attributes
  (union global-attributes
         #{:accept :accept-charset :action :autocomplete
           :enctype :method :name :novalidate :target}))

(def allowed-form-children
  #{:input :textarea :button :select :option :optgroup :fieldset :label})

(defn allowed-form-attributes? [attr]
  (subset? (keys attr) allowed-form-attributes))

(defn allowed-form-children? [children]
  (let [child-tags (filter #(not (string? %1)) children)]
    (subset? (reduce #(conj %1 (first %2)) #{} child-tags) allowed-form-children))) ;TODO: Use into?

(defn form [attr & children]
  (assert (allowed-form-attributes? attr))
  (assert (allowed-form-children? children))
  (apply el :form attr children))

(defn input [attr]
  (el :input attr))
