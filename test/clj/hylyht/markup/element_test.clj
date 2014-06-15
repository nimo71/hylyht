(ns hylyht.markup.element-test
  (:require [clojure.test :refer :all]
            [hylyht.markup.element :refer :all])
  (:import [hylyht.markup Markup]
           [hylyht.markup.element Element]))

(deftest creates-element
  (testing "without attributes or children"
    (is (= (Element. :test {} nil))
        (element :test)))

  (testing "with no children"
    (is (= (Element. :test {:attr1 "attr"} nil)
           (element :test :attr1 "attr"))))

  (testing "with one child and attributes"
    (is (= (Element. :test {:attr1 "attr"} ["child"])
           (element :test :attr1 "attr" "child")))))



(deftest creates-element-markup-string
  (testing "element markup is created"
    (is (= "<name attr1=\"value1\" attr2=\"value2\">Child Text</name>"
           (.markup-str (element "name" :attr1 "value1" :attr2 "value2" "Child Text")))))

  (testing "nested element markup is created"
    (is (= "<parent p1=\"v1\" p2=\"v2\"><child c1=\"v1\"></child></parent>"
           (.markup-str (element :parent :p1 "v1" :p2 "v2" (element :child :c1 "v1")))))))
