(ns hylyht.html
  (:require [hylyht.markup :as markup :refer [element declaration element-str]]
            [clojure.set :refer [union subset?]]))

;;TODO: Should children be restricted? Maybe have strict mode or warning?
;;TODO: Should be able to parse html and generate markup components

(def global-attributes #{:id :class})

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

; Conditional comment for IE browsers
(defn <!-- [condition & children]
  (let [cond-start   (str"[if " condition "]>")
        children-str (apply element-str children)
        cond-end     "<![endif]"]
    (markup/<!-- (str cond-start children-str cond-end))))

(defn script [& attr]
  (apply element :script attr))

(defn link [& attr]
  (apply element :link attr))

(defn body [& children]
  (apply element :body children))

(defn div [& attr-children]
  (apply element :div attr-children))

(defn p [content]
  (assert (string? content))
  (element :p {} content))


; form element

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
      (let [[_ [tag & _]] child]
        (contains? allowed-form-children tag))))

(defn allowed-form-children? [children]
  (reduce #(and %1 (allowed-form-child? %2)) true children))

(defn form [& attr-children]
  (let [el (apply element :form attr-children)
        [_ [_ attrs & children]] el]
    (assert (allowed-form-attributes? attrs))
    (assert (apply allowed-form-children? children))
    el))

(defn input [& attr]
  (apply element :input attr))

(defn label [& attr-children]
  (apply element :label attr-children))
