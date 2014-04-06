(ns hylyht.markup)

;;TODO: create markup package with element and attribute namespaces

(defn el [el-name attrs & children]
  (assert keyword? el-name)
  [:element el-name attrs (into [] children)])

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

;;TODO: content should be optional, if no content could take the form <name attr="value" .../>
(defn element-str [el]
  (let [[kind tag attrs children] el
         tagname (name tag)
         open-tag (str "<" tagname " " (attr-str attrs) ">")
         close-tag (str "</" tagname ">")]

    (assert (= kind :element))
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

(defn declaration [& decs]
  [:declaration (vec decs)])

(defn declaration-str [declaration]
  (let [[kind decs] declaration]
    (assert (= kind :declaration))
    (str "<" (reduce #(str %1 " " %2) decs) ">")))
