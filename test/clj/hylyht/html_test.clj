(ns hylyht.html-test
  (:require [clojure.test :refer :all]
            [hylyht.html :refer :all]
            [hylyht.markup :refer [element element-str]]))

(deftest creates-p
  (testing "Creates p html element"
    (is (= [:element :p {} ["some content"]]
           (p "some content")))))

(deftest creates-form

  (testing "Creates empty form"
    (is (= [:element :form {} []] (form {}))))

  (testing "Creates empty form with attributes"
    (is (= [:element :form {:action "url", :method "post"} []]
           (form {:action "url", :method "post"}))))

  (testing "Assert attributes are correct"
    (is (thrown? AssertionError
          (form {:unknown "value"}))))

  (testing "Creates with input elements"
    (is (= [:element :form {} [[:input {:type "text", :name "t1"} []]
                      [:input {:type "text", :name "t2"} []]]]
           (form {} (input {:type "text", :name "t1"})
                    (input {:type "text", :name "t2"})))))

  (testing "Creates with strings"
    (is (= [:element :form {} ["child"]]
           (form {} "child"))))

  (testing "Assert form children are correct element types"
    (is (thrown? AssertionError
          (form {} (element :unknown "value"))))))

(deftest creates-input
  (testing "Creates empty input"
    (is (= [:element :input {:type "text", :name "t1"} []]
           (input {:type "text", :name "t1"})))))

(deftest creates-form
  (testing "Creates form"
    (is (= [:element :form {:method "post", :action "/login", :id "login_form"}
             ["Username: "
              [:element :input {:id "username", :type "text"} []]
              "Password: "
              [:element :input {:id "password", :type "text"} []]]]
           (form :id "login_form" :action "/login" :method "post"
             "Username: "
             (input :id "username" :type "text")
             "Password: "
             (input  :id "password" :type "text"))))))


;(deftest creates-form-string
;    (testing "Creates form string"
;    (is (= ""
;           (element-str (form :id "login_form" :action "/login" :method "post"
;             "Username: "
;             (input :id "username" :type "text")
;             "Password: "
;             (input  :id "password" :type "text")))))))
