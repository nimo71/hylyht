(ns hylyht.html-test
  (:require [clojure.test :refer :all]
            [hylyht.html :refer :all]))

(deftest creates-p
  (testing "Creates p html element"
    (is (= [:p {} ["some content"]]
           (p "some content")))))

(deftest creates-form

  (testing "Creates empty form"
    (is (= [:form {} []] (form {}))))

  (testing "Creates empty form with attributes"
    (is (= [:form {:action "url", :method "post"} []]
           (form {:action "url", :method "post"}))))

  (testing "Asserts attributes are correct"
    (is (thrown? AssertionError
          (form {:unknown "value"})))))

