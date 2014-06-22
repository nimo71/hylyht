(ns hylyht.html-test
  (:refer-clojure :exclude [meta])
  #+cljs (:require-macros [cemerick.cljs.test :refer (is deftest with-test run-tests testing test-var)])

  #+clj (:require [clojure.test :as t :refer (is deftest with-test run-tests testing)]
                  [hylyht.html :as html]
                  [hylyht.markup.element :refer [element]]
                  [hylyht.markup.declaration :refer [declaration]]
                  [hylyht.markup.comment :as cmt])

  #+cljs (:require [cemerick.cljs.test :as t ]
                   [hylyht.html :as html]
                   [hylyht.markup.element :refer [element]]
                   [hylyht.markup.declaration :refer [declaration]]
                   [hylyht.markup.comment :as cmt]))

(deftest creates-doctype
  (testing "Creates doctype"
    (is (= (declaration "!DOCTYPE" "html")
           (html/doctype "html")))))

(deftest creates-meta
  (testing "Creates meta declaration"
    (is (= (declaration :meta :a1 "v1")
           (html/meta :a1 "v1")))))

(deftest creates-title
  (testing "Creates title element"
    (is (= (element :title "the title")
           (html/title "the title")))))

(deftest creates-comment
  (testing "Creates standard comment"
    (is (= (cmt/<!-- "test")
           (html/<!-- "test"))))

  (testing "Creates conditional comment"
    (is (= (cmt/<!-- "[if lt IE 9]><script src=\"html5.js\"></script><![endif]")
           (html/<!-- "lt IE 9" (html/script :src "html5.js"))))))

(deftest creates-script
  (testing (= (element :script :src "http://some/url")
              (html/script :src "http://some/url"))))

(deftest creates-link
  (testing (= (element :link :rel "stylesheet" :href "css/normalize.css")
              (html/link :rel "stylesheet" :href "css/normalize.css"))))

(deftest creates-div
  (testing "Creates div html element"
    (is (= (element :div :class "abc" "some content")
           (html/div :class "abc" "some content")))))

(deftest creates-p
  (testing "Creates p html element"
    (is (= (element :p "some content")
           (html/p "some content")))))

(deftest creates-form

  (testing "Creates empty form"
    (is (= (element :form)
           (html/form))))

  (testing "Creates empty form with attributes"
    (is (= (element :form :action "url", :method "post")
           (html/form :action "url", :method "post"))))

  (testing "Creates with input elements"
    (is (= (element :form
             (element :input :type "text", :name "t1")
             (element :input :type "text", :name "t2"))
           (html/form
             (html/input :type "text", :name "t1")
             (html/input :type "text", :name "t2")))))

  (testing "Creates with strings"
    (is (= (element :form "child")
           (html/form "child"))))

  (testing "Creates form with element and string children"
    (is (= (element :form :method "post", :action "/login", :id "login_form"
             "Username: "
             (element :input :id "username", :type "text")
             "Password: "
             (element :input :id "password", :type "text"))

           (html/form :id "login_form" :action "/login" :method "post"
             "Username: "
             (html/input :id "username" :type "text")
             "Password: "
             (html/input :id "password" :type "text"))))))

(deftest creates-input
  (testing "Creates empty input"
    (is (= (element :input :type "text", :name "t1")
           (html/input :type "text", :name "t1")))))

(deftest creates-a
  (testing "Creates anchor"
    (is (= (element :a :href "./url.html" "anchor")
           (html/a :href "./url.html" "anchor")))))
