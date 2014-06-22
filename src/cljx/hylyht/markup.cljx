(ns hylyht.markup
  #+cljs (:require [hylyht.logging :as log]))

#+cljs (def logger (log/get-logger "hylyht.markup"))

(defprotocol Markup
  (markup-str [markup]))

; TODO: All of the types need to be valid for cljs as well as clj - can String work for cljs??
(extend-protocol Markup

  #+clj String
  #+cljs string
  (markup-str [this]
    #+cljs (log/info logger "(markup-str String)")
    this)

  #+cljs cljs.core/IndexedSeq
  #+clj clojure.lang.IndexedSeq
  (markup-str [this]
    #+cljs (log/info logger "(markup-str IndexedSeq)")
    (reduce #(str %1 (markup-str %2)) "" this))


  ;#+clj clojure.lang/ISeq
  ;#+cljs cljs.core/ISeq
  ;(markup-str [this]
  ;  (reduce #(str %1 (markup-str %2)) "" this))

  ;#+clj clojure.lang/Seqable
  ;#+cljs cljs.core/ISeqable
  ;(markup-str [this]
  ;  (markup-str (seq this)))


  ;#+clj clojure.lang.ArraySeq
  ;#+cljs cljs.core.ArraySeq
  ;(markup-str [this]
  ;  (reduce #(str %1 (markup-str %2)) "" this))

  #+clj clojure.lang.PersistentVector
  #+cljs cljs.core/PersistentVector
  (markup-str [this]
    #+cljs (log/info logger "(markup-str PersistentVector)")
    (reduce #(str %1 (markup-str %2)) "" this))

  nil
  (markup-str [_]
    #+cljs (log/info logger "(markup-str nil)")
    ""))

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
