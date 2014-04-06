(ns hylyht.page
  (:require [hylyht.html :refer [doctype html head body]]))

(defn page []
  [(doctype "html")
   (html
     (head
      (meta :charset "utf=8")
      )
     (body))])

(comment
"<!doctype html>
 <html lang=\"en\">
 <head>
    <meta charset=\"utf-8\">
    <title>Hylyht</title>
    <!--[if lt IE 9]>
    <script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>
    <![endif]-->
    <link rel=\"stylesheet\" href=\"css/normalize.css\">
    <link rel=\"stylesheet\" href=\"css/main.css\">
</head>
<body>
    <!--[if lt IE 7]>
        <p class=\"browsehappy\">You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <div id=\"content\"></div>
    <script src=\"js/hylyht.js\"></script>
    <script>hylyht.main.init();</script>
</body>
</html>")
