(ns hylyht.markup)

(defn el [el-name attrs & children]
  [el-name attrs children])

;;TODO: probably need to optimise, use reduce?
(defn attr-str [attr-map]
  (let [attr-strings (reverse (for [[k v] attr-map] (str " " (name k) "=\"" v "\"")))
        attr-string (apply str attr-strings)]
    (subs attr-string 1)))

;;TODO: content should be optional, if not there then element should take the form <name attr="value" .../>
(defn element-str [el]
  (let [tagname (first el)
        attrs (second el)
        children (last el)
        open-tag (str "<" tagname " " (attr-str attrs) ">")
        close-tag (str "</" tagname ">")]

    (str open-tag
      (reduce (fn [acc child]
                (str acc
                  (if (string? child)
                    child
                    (element-str child))))
              children)
      close-tag)))
