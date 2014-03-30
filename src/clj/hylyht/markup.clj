(ns hylyht.markup)

(defn element [el-name attributes content]
  [el-name attributes content])

;;TODO: probably need to optimise, use reduce?
(defn attr-str [attr-map]
  (let [attr-strings (reverse (for [[k v] attr-map] (str " " (name k) "=\"" v "\"")))
        attr-string (apply str attr-strings)]
    (subs attr-string 1)))

;;TODO: content should be optional, if not there then element should take the form <name attr="value" .../>
(defn element-str [tagname attr content]
  (str "<" tagname " " (attr-str attr) ">" content "</" tagname ">"))
