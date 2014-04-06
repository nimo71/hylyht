(ns hylyht.markup)

(defn ^:private separate-attrs-and-children [& params]
  (loop [attrs-then-children params
         attrs {}]
    (let [[attr-name attr-value & remainder] attrs-then-children]
      (if (and (keyword? attr-name) (string? attr-value))
        (recur remainder (assoc attrs attr-name attr-value))
        {:attributes attrs, :children attrs-then-children}))))

;;TODO: probably need to optimise, use reduce?
(defn attr-str [attr-map]
  (let [attr-strings (for [[k v] attr-map] (str " " (name k) "=\"" v "\""))
        attr-string (apply str attr-strings)]
    (subs attr-string 1)))

(defn declaration [& decs]
  (if (keyword? (first decs))
    (let [[dec-name & params] decs
          {attrs :attributes, children :children} (apply separate-attrs-and-children params)]
      (cond
        (empty? attrs)    [:declaration [dec-name children]]
        (empty? children) [:declaration [dec-name attrs]]
        :else             [:declaration [dec-name attrs children]]))
    [:declaration (vec decs)]))

(defn declaration-str [declaration]
  (let [[kind decs] declaration]
    (assert (= kind :declaration))

    (if (keyword? (first decs))
      (let [[dec-name & attrs] decs]
        (str "<" (name dec-name) " " (attr-str (first attrs)) ">"))

      (str "<" (subs (reduce #(str %1 " " %2) "" decs) 1) ">"))))

(defn <!-- [comment]
  [:comment [:<!-- comment]])

(defn comment-str [comment]
  (let [[kind [_ content]] comment]
    (str "<!--" comment "-->")))

(defn el [el-name attrs & children]
  (assert keyword? el-name)
  [:element [el-name attrs (into [] children)]])

(defn element [el-name & attrs-then-children]
  (if (map? (first attrs-then-children))
    (apply el el-name (first attrs-then-children) (rest attrs-then-children))

    (let [attrs-and-children (apply separate-attrs-and-children attrs-then-children)
          attrs (:attributes attrs-and-children)
          children (:children attrs-and-children)]
      (apply el el-name attrs children))))

;;TODO: content should be optional, if no content could take the form <name attr="value" .../>
(defn element-str [el]
  (let [[kind [tag attrs children]] el
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

;; TODO: markup-str
(defn markup-str [& markup]
  (reduce #(let [markup %1
                 [kind ] %2])
          ""
          markup))
