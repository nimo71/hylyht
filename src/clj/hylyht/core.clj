(ns hylyht.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]))

(defroutes app-routes
  ;;(GET "/" [] "<p>Hello from compojure</p>")
  (resources "/")
  (not-found "<h1>Page Not Found</h1>"))

(def handler
  (site app-routes))
