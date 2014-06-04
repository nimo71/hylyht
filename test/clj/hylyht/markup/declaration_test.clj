(ns hylyht.markup.declaration-test
  (:require [clojure.test :refer :all]
            [hylyht.markup.declaration :refer :all]))

(deftest creates-declaration-markup-string
  (testing "declaration markup is created"
    (is (= "<!DOCTYPE html>"
           (.markup-str (declaration "!DOCTYPE" "html"))))))
