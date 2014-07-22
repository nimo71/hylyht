(ns hylyht.browser.dom-test
  (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [hylyht.html :as html]
            [hylyht.browser.dom :as dom]))

(deftest attaches-elements
  (testing "Creates peer"
    (let [markup   (html/html "test")
          root     (dom/attach markup)
          peer     (.-peer root)
          helper   (goog.dom.DomHelper. peer)
          el       (.getElement helper peer)
          tag-name (.-tagName el)]

    (is (= "HTML" tag-name))))

  (comment testing "Descends to children, creating peers"
    (let [markup (html/html
                   (html/body
                     (html/p "Testing")))

          root   (dom/attach markup)
          peer   (.-peer root)
          helper (goog.dom.DomHelper. peer)]

      (println (str "peer=" peer))
      (is (true? (.findNode helper
                            peer
                            (fn [node]
                              (println (str "node=" node))
                              (let [helper (DomHelper. node)]
                                (= (.textContent helper node) "Testing")))))))))

