(ns hylyht.main
  (:require [hylyht.markup :refer [markup-str]]
            [hylyht.registration-form :as reg-form]
            [hylyht.login-form :as login-form]
            [hylyht.browser.history :refer [history]]
            [hylyht.logging :as log]))

(def logger (log/get-logger "hylyht.main"))

(defn routes [{:keys [token, type, navigation?] :as event}]

  (log/info logger (str "token=" token ", type=" type ", navigation?=" navigation?))

  (let [document    (goog.dom.DomHelper. js/document)
        content-div (.getElement document "content")
        content     (token {:login login-form/create} reg-form/create)
        markup      (apply markup-str (content))
        fragment    (.htmlToDocumentFragment document markup)]

    (.removeChildren document content-div)
    (.append document content-div fragment)))


(defn ^:export init []
  (let [h (history routes)]))


;;(comment
  ;; log to the console
  (log/start-display (log/console-output))
  ;; log to to the "fancy" window
  ;;(log/start-display (log/fancy-output "main"))
  ;; change the logging level
  (log/set-level logger :fine)
  ;;)

;TODO:
;  * listen for all types of events
;     - is history only causing navigation events to be raised
;     - other types of events
;         - submit form events
;         - focus events
;         - custom events e.g. validation?
;  * event handling using core.asynch
;  * make remote calls using json or clojure alternative? (maps?)
;  * create renderable protocol
;  * create component protocol
;  * create event-handler protocol
;  * handle dom within page
;      - add, remove component
;  * pass events down into page
;  * pass events into components
;  * composite components
;  * raising events in components
;  * include styles in component
;      - remote stylesheets
;  * create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
;       - page as value, would be good if it appeared to be immutable
