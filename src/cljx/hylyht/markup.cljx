(ns hylyht.markup)

(defn el [el-name attrs & children]
  (assert keyword? el-name)
  [el-name attrs (into [] children)])

; (defn separate-attrs-and-children [& params]
;  (loop [attrs-then-children params, attrs {}]
;
;    (let [attr-name (first attrs-then-children)
;          attr-value (second attrs-then-children)]
;
;      (if (and (keyword? attr-name) (string? attr-value))
;        (recur (rest(rest attrs-then-children)) (assoc attrs attr-name attr-value));(nthrest attrs-then-children 2) (assoc attrs attr-name attr-value))
;        {:attributes attrs, :children attrs-then-children}))))

(defn separate-attrs-and-children [& params]
  (loop [attrs-then-children params
         attrs {}]
    (let [[attr-name attr-value & remainder] attrs-then-children]
      (if (and (keyword? attr-name) (string? attr-value))
        (recur remainder (assoc attrs attr-name attr-value))
        {:attributes attrs, :children attrs-then-children}))))

(defn element [el-name & attrs-then-children]
  (if (map? (first attrs-then-children))
    (apply el el-name (first attrs-then-children) (rest attrs-then-children))

    (let [attrs-and-children (apply separate-attrs-and-children attrs-then-children)
          attrs (:attributes attrs-and-children)
          children (:children attrs-and-children)]
      (apply el el-name attrs children))))

;;TODO: probably need to optimise, use reduce?
(defn attr-str [attr-map]
  (let [attr-strings (for [[k v] attr-map] (str " " (name k) "=\"" v "\""))
        attr-string (apply str attr-strings)]
    (subs attr-string 1)))

;;TODO: content should be optional, if not there then element should take the form <name attr="value" .../>
(defn element-str [el]
  (let [tagname (str (name (first el)))
        attrs (second el)
        children (last el)
        open-tag (str "<" tagname " " (attr-str attrs) ">")
        close-tag (str "</" tagname ">")]

    (str open-tag
         (if (seq children)
           (reduce (fn [markup child]
               (str markup
                    (if (string? child)
                      child
                      (element-str child))))
               ""
               children))
         close-tag)))
