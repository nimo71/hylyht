(ns hylyht.browser.events-test
  (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [hylyht.html :as dom]
            [hylyht.browser.events :as events]))

(comment deftest listens-to-events
  (testing "Listens to click event"
    (let [link     (dom/a :href "./dest" "test link")
          document (dom/html
                     (dom/body
                       link))]
      (events/listen link (fn [] ))
      (dom/render document)
      (dom/click link))



    (is (= (declaration "!DOCTYPE" "html")
           (html/doctype "html")))))
