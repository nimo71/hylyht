(ns hylyht.markup-test
  (:require [clojure.test :refer :all]
            [hylyht.markup :refer :all]))

(deftest constructs-element

  (testing "Elements with string content are constructed"
    (is (= [:element [:t {:a1 "v1", :a2 "v2"} ["content"]]]
           (element :t {:a1 "v1", :a2 "v2"} "content"))))

  (testing "Elements are constructed without attr map"
    (is (= [:element [:t {:a1 "v1", :a2 "v2"} ["content"]]]
           (element :t :a1 "v1" :a2 "v2" "content"))))

  (testing "Elements with single element children are constructed"
    (is (= [:element [:t1 {:a1 "v1"} [[:element [:t2 {:a2 "v2"} ["content"]]]]]]
           (element :t1 {:a1 "v1"}
               (element :t2 {:a2 "v2"} "content")))))

  (testing "Elements with multiple element children are constructed"
    (is (= [:element [:t1 {:a1 "v1"}
              [[:element [:t2 {:a2 "v2"} ["c2"]]]
               [:element [:t3 {:a3 "v3"} ["c3"]]]]]]
           (element :t1 {:a1 "v1"}
               (element :t2 {:a2 "v2"} "c2")
               (element :t3 {:a3 "v3"} "c3"))))))

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

(deftest creates-declaration
  (testing "declaration is constructed"
    (is (= [:declaration ["!DOCTYPE" "html"]]
           (declaration "!DOCTYPE" "html"))))

  (testing "declaration is constructed with attributes"
    (is (= [:declaration [:meta {:a1 "v1", :a2 "v2"}]]
           (declaration :meta :a1 "v1" :a2 "v2")))))

(deftest creates-declaration-str
  (testing "Creates a declaration with children string"
    (is (= "<!DOCTYPE html>"
           (declaration-str (declaration "!DOCTYPE" "html")))))

  (testing "Creates declaration with attributes string"
    (is (= "<meta charset=\"utf-8\">"
           (declaration-str (declaration :meta :charset "utf-8"))))))

(deftest creates-comment
  (testing "Comment is constructed"
    (is [:comment [:<!-- "contents"]]
        (<!-- "contents"))))

(deftest creates-comment-string
  (testing "Creates comment string"
    (is "<!-- the comment -->"
        (<!-- "the comment"))))

;(deftest prepends-to-selected-element
;  (testing "prepends the given content to the elements matching the selector"
;    (is )))
