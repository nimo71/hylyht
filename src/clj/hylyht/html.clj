(ns hylyht.html
  (:require [hylyht.markup :refer [el]]
            [clojure.set :refer [subset?]]))

(defn p [content]
  (assert (string? content))
  (el :p {} content))

(def form-attributes
  #{:accept :accept-charset :action :autocomplete
    :enctype :method :name :novalidate :target})

(defn form [attr & children]
  (assert (subset? (keys attr) form-attributes))
  (apply el :form attr children))
