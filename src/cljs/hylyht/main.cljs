(ns hylyht.main
  (:require [domina :refer [append! by-id]]
            [hylyht.markup :refer [markup-str]]
            [hylyht.registration-form :as reg-form]
            [hylyht.login-form :as login-form]
            [hylyht.browser.history :refer [history, set-token]]))

(defn routes [{:keys [token, type, navigation?] :as event}]

  (js/alert (str "token=" token ", type=" type ", navigation?=" navigation?))

  (let [content (token {:login login-form/create}
              reg-form/create)]

      (append! (by-id "content") (apply markup-str (content)))))


(defn ^:export init []
  (let [h (history routes)]
    ))

;TODO:
;  * debug to console...
;  * remove domina and use google clojure instead, wrap clojure...
;  * add html render function to traverse the markup and stringify
;  * create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
;       - page as value, would be good if it appeared to be immutable
