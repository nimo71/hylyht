(ns hylyht.markup-test
  (:require [clojure.test :refer :all]
            [hylyht.markup :refer :all]))

(deftest constructs-element

  (testing "Elements with string content are constructed"
    (is (= [:t {:a1 "v1", :a2 "v2"} ["content"]]
           (element :t {:a1 "v1", :a2 "v2"} "content"))))

  (testing "Elements are constructed without attr map"
    (is (= [:t {:a1 "v1", :a2 "v2"} ["content"]]
           (element :t :a1 "v1" :a2 "v2" "content"))))

  (testing "Elements with single element children are constructed"
    (is (= [:t1 {:a1 "v1"} [[:t2 {:a2 "v2"} ["content"]]]]
           (element :t1 {:a1 "v1"}
               (element :t2 {:a2 "v2"} "content")))))

  (testing "Elements with multiple element children are constructed"
    (is (= [:t1 {:a1 "v1"}
              [[:t2 {:a2 "v2"} ["c2"]]
               [:t3 {:a3 "v3"} ["c3"]]]]
           (element :t1 {:a1 "v1"}
               (element :t2 {:a2 "v2"} "c2")
               (element :t3 {:a3 "v3"} "c3"))))))

;(deftest prepends-to-selected-element
;  (testing "prepends the given content to the elements matching the selector"
;    (is )))

(deftest creates-attr-str
  (testing "Takes a map and creates name value pair strings for markup"
    (is (= "a2=\"v2\" a1=\"v1\"" (attr-str {:a1 "v1", :a2 "v2"})))))

(deftest creates-element-str

  (testing "Creates a markup string from an element"
    (is (= "<el a2=\"v2\" a1=\"v1\">content</el>"
           (element-str (element :el {:a1 "v1", :a2 "v2"} "content")))))

  (testing "Creates a markup string from an element without children"
    (is (= "<tag id=\"attr\"></tag>"
           (element-str (element :tag {:id "attr"})))))

  (testing "Creates a markup string from an element with one child"
    (is (= "<tag id=\"attr\"><child id=\"child\"></child></tag>"
           (element-str (element :tag {:id "attr"}
                            (element :child {:id "child"}))))))

  (testing "Creates a markup string from an element with multiple children"
    (is (= "<tag id=\"attr\"><child id=\"child1\"></child><child id=\"child2\"></child><child id=\"child3\"></child></tag>"
           (element-str (element :tag {:id "attr"}
                            (element :child {:id "child1"})
                            (element :child {:id "child2"})
                            (element :child {:id "child3"})))))))
