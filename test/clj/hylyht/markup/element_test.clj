(ns hylyht.markup.element-test
  (:require [clojure.test :refer :all]
            [hylyht.markup.element :refer :all])
  (:import [hylyht.markup Markup]))

(deftest creates-element-markup-string
  (testing "element markup is created"
    (is (= "<name attr1=\"value1\" attr2=\"value2\">Child Text</name>"
           (.markup-str (element "name" {:attr1 "value1", :attr2 "value2"} "Child Text"))))))
