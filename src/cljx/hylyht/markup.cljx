(ns hylyht.markup
  (:gen-class))

(defprotocol Markup
  (markup-str [markup]))

; TODO: All of the types need to be valid for cljs as well as clj - can String work for cljs??
(extend-protocol Markup
  String
  (markup-str [this] this)

  clojure.lang.ISeq
  (markup-str [this]
    (reduce #(str %1 (markup-str %2)) "" this))

  nil
  (markup-str [_] ""))

; helper to create an attribute string
(defn attr-str [attr-map]
  (let [attr-strings (for [[k v] attr-map] (str " " (name k) "=\"" v "\""))
        attr-string  (apply str attr-strings)]
    (if (> (count attr-string) 1) (subs attr-string 1) "")))

; helper to separate attributes and children from a sequence
(defn separate-attrs-and-children [& params]
  (loop [attrs-then-children params
         attrs {}]
    (let [[attr-name attr-value & remainder] attrs-then-children]
      (if (and (keyword? attr-name) (string? attr-value))
        (recur remainder (assoc attrs attr-name attr-value))
        {:attr attrs, :children attrs-then-children}))))
