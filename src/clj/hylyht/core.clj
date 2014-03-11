(ns hylyht.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]
            [hylyht.login :refer [authenticate-user]]))

(defroutes app-routes
  ;; to serve document root address
  (GET "/" [] "<p>Hello from compojure</p>")
  (POST "/login" [email password] (authenticate-user email password))
  (resources "/")
  (not-found "Page non found"))

(def handler
  (site app-routes))
