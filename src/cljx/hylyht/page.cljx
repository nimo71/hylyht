(ns hylyht.page
  (:require [hylyht.html :refer [doctype html head meta title <!-- script link body p div]]
            [hylyht.markup :refer [markup-str]]))

(defn create-page []
  [(doctype "html")
   (html :lang "en"
     (head
       (meta :charset "utf-8")
       (title "Hylyht")
       (<!-- "lt IE 9" (script :src "http://html5shiv.googlecode.com/svn/trunk/html5.js"))
       (link :rel "stylesheet" :href "css/normalize.css")
       (link :rel "stylesheet" :href "css/main.css"))
     (body
       (<!-- "lt IE 7"
         (p :class "browsehappy"
           "You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a> to improve your experience."))
       (div :id "content")
       (script :src "js/hylyht.js")
       (script "hylyht.main.init();")))])

(defn render-page [page]
  (apply markup-str page))
