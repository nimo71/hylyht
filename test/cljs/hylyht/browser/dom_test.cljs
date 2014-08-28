(ns hylyht.browser.dom-test
  (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])
  (:require [cemerick.cljs.test :as t]
            [hylyht.html :as html]
            [hylyht.browser.dom :as dom]))

(deftest attaches-elements
  (testing "Creates peer"
    (let [markup   (html/html "test")
          root     (dom/attach markup)
          peer     (:peer root)
          helper   (goog.dom.DomHelper. peer)
          el       (.getElement helper peer)
          tag-name (.-tagName el)]

    (is (= "HTML" tag-name))))

  (testing "Descends to children, creating peers"
    (let [markup (html/html
                   (html/body
                     (html/p "Testing")))

          root   (dom/attach markup)
          peer   (:peer root)
          helper (goog.dom.DomHelper. peer)]

      (println (str "peer=" peer))

      (let [body-node (first (:children root))
            p-node    (first (:children body-node))
            testing-node (first (:children p-node))]

        (is (= (:peer testing-node) "Testing")))))

  (comment testing "Creates peer element hierarchy"
    (let [markup  (html/html
                    (html/body
                      (html/p "Testing")))

          root    (dom/attach markup)
          peer    (:peer root)
          helper  (goog.dom.DomHelper. peer)
          html-el (.getFirstElementChild helper peer)
          body-el (.getFirstElementChild helper html-el)
          p-el    (.getFirstElementChild helper body-el)
          testing (.getFirstElementchild helper p-el)]
        (is (= "Testing" testing)))
      ))

