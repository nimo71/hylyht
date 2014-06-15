(ns hylyht.markup-test
  (:require [clojure.test :refer :all]
            [hylyht.markup :refer :all]
            [hylyht.markup.declaration :refer [declaration]]
            [hylyht.markup.element :refer [element]]))

(deftest creates-attr-str
  (testing "Takes a map and creates name value pair strings for markup"
    (is (= "a2=\"v2\" a1=\"v1\"" (attr-str {:a1 "v1", :a2 "v2"})))))

(deftest separates-attributes-and-children
  (testing "no attributes or children"
    (is (= {:attr {}, :children nil}
           (separate-attrs-and-children))))

  (testing "attributes without children"
    (is (= {:attr {:a1 "v1", :a2 "v2"}, :children nil}
           (separate-attrs-and-children :a1 "v1" :a2 "v2"))))

  (testing "attributes and children"
    (is (= {:attr {:a1 "v1", :a2 "v2"}, :children ["c1" "c2"]}
           (separate-attrs-and-children :a1 "v1" :a2 "v2" "c1" "c2"))))

    (testing "children without attributes"
    (is (= {:attr {}, :children ["c1" "c2"]}
           (separate-attrs-and-children "c1" "c2")))))
