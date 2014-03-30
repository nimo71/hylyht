(ns hylyht.html-test
  (:require [clojure.test :refer :all]
            [hylyht.html :refer :all]
            [hylyht.markup :refer [el]]))

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

  (testing "Assert attributes are correct"
    (is (thrown? AssertionError
          (form {:unknown "value"}))))

  (testing "Creates with input elements"
    (is (= [:form {} [[:input {:type "text", :name "t1"} []]
                      [:input {:type "text", :name "t2"} []]]]
           (form {} (input {:type "text", :name "t1"})
                    (input {:type "text", :name "t2"})))))

  (testing "Assert form children are correct element types"
    (is (thrown? AssertionError
          (form {} (el :unknown {}))))))

(deftest creates-input
  (testing "Creates empty input"
    (is (= [:input {:type "text", :name "t1"} []]
           (input {:type "text", :name "t1"})))))
