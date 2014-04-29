(ns hylyht.html-test
  (:require [clojure.test :refer :all]
            [hylyht.html :refer :all]
            [hylyht.markup :refer [element element-str]]))

(deftest creates-doctype
  (testing "Creates doctype"
    (is (= [:declaration ["!DOCTYPE" "html"]]
           (doctype "html")))))

(deftest creates-meta
  (testing "Creates meta declaration"
    (is (= [:declaration [:meta {:a1 "v1"}]]
           (meta :a1 "v1")))))

(deftest creates-title
  (testing "Creates title element"
    (is (= [:element [:title {} ["the title"]]]
           (title "the title")))))

(deftest creates-conditional-comment
  (testing "Creates conditional comment"
    (is (= [:comment [:<!-- "[if lt IE 9]><script src=\"html5.js\"></script><![endif]"]]
           (<!-- "lt IE 9" (script :src "html5.js"))))))

(deftest creates-script
  (testing (= [:element [:script {:src "http://some/url"} []]]
              (script :src "http://some/url"))))

(deftest creates-link
  (testing (= [:element [:link {:rel "stylesheet" :href "css/normalize.css"} []]]
              (link :rel "stylesheet" :href "css/normalize.css"))))

(deftest creates-div
  (testing "Creates div html element"
    (is (= [:element [:div {:class "abc"} ["some content"]]]
           (div :class "abc" "some content")))))

(deftest creates-p
  (testing "Creates p html element"
    (is (= [:element [:p {} ["some content"]]]
           (p "some content")))))

(deftest creates-form

  (testing "Creates empty form"
    (is (= [:element [:form {} []]]
           (form {}))))

  (testing "Creates empty form with attributes"
    (is (= [:element [:form {:action "url", :method "post"} []]]
           (form {:action "url", :method "post"}))))

  (testing "Assert attributes are correct"
    (is (thrown? AssertionError
          (form {:unknown "value"}))))

  (testing "Creates with input elements"
    (is (= [:element [:form {} [
             [:element [:input {:type "text", :name "t1"} []]]
             [:element [:input {:type "text", :name "t2"} []]]]]]
           (form {} (input {:type "text", :name "t1"})
                    (input {:type "text", :name "t2"})))))

  (testing "Creates with strings"
    (is (= [:element [:form {} ["child"]]]
           (form {} "child"))))

  (testing "Assert form children are correct element types"
    (is (thrown? AssertionError
          (form {} (element :unknown "value")))))

  (testing "Creates form with element and string children"
    (is (= [:element [:form {:method "post", :action "/login", :id "login_form"} [
             "Username: "
             [:element [:input {:id "username", :type "text"} []]]
             "Password: "
             [:element [:input {:id "password", :type "text"} []]]]]]

           (form :id "login_form" :action "/login" :method "post"
             "Username: "
             (input :id "username" :type "text")
             "Password: "
             (input  :id "password" :type "text"))))))

(deftest creates-input
  (testing "Creates empty input"
    (is (= [:element [:input {:type "text", :name "t1"} []]]
           (input {:type "text", :name "t1"})))))

(deftest creates-a
  (testing "Creates anchor"
    (is (= [:element [:a {:href "./url.html"} ["anchor"]]]
           (a :href "./url.html" "anchor")))))
