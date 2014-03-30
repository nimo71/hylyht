(ns hylyht.html-test
  (:require [clojure.test :refer :all]
            [hylyht.html :refer :all]))

(deftest creates-p-tag
  (testing "creates p html element"
    (is (= ["p" {} '("some content")]
           (p "some content")))))
