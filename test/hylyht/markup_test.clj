(ns hylyht.markup-test
  (:require [clojure.test :refer :all]
            [hylyht.markup :refer :all]))

(deftest test-element-constructor
  (testing "Elements are constructed"
    (is (= ["tagname" {:a1 "v1", :a2 "v2"} "content"]
           (element "tagname" {:a1 "v1", :a2 "v2"} "content")))))

(deftest test-attr-str
  (testing "Attributes takes a map and creates markup attributes"
    (is (= "a1=\"v1\" a2=\"v2\"" (attr-str {:a1 "v1", :a2 "v2"})))))

(deftest test-element-str
  (testing "Creates an elements markup string")
    (is (= "<el a1=\"v1\" a2=\"v2\">content</el>"
           (element-str "el" {:a1 "v1", :a2 "v2"} "content"))))
