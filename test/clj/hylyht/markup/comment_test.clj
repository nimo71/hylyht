(ns hylyht.markup.comment-test
  (:require [clojure.test :refer :all]
            [hylyht.markup.comment :refer :all]))

(deftest creates-comment-markup-string
  (testing "comment markup is created"
    (is (= "<!-- comment content -->"
           (.markup-str (<!-- "comment content"))))))
