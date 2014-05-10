(ns hylyht.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [hylyht.page :refer [create-page render-page]]))

(defroutes app-routes
  (GET "/app.html" [] (render-page (create-page)))
  (resources "/")
  (not-found "<h1>Page Not Found</h1>"))

(def handler
  (site app-routes))
