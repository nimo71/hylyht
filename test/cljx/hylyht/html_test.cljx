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

  (testing "Creates with strings"
    (is (= [:form {} ["child"]]
           (form {} "child"))))

  (testing "Assert form children are correct element types"
    (is (thrown? AssertionError
          (form {} (el :unknown "value"))))))

(deftest creates-input
  (testing "Creates empty input"
    (is (= [:input {:type "text", :name "t1"} []]
           (input {:type "text", :name "t1"})))))

(deftest creates-form
  (testing "Creates form"
    (is (= [:form {:method "post", :action "/login", :id "login_form"}
             ["Username: "
              [:input {:id "username", :type "text"} []]
              "Password: "
              [:input {:id "password", :type "text"} []]]]
           (form :id "login_form" :action "/login" :method "post"
             "Username: "
             (input :id "username" :type "text")
             "Password: "
             (input  :id "password" :type "text"))))))
