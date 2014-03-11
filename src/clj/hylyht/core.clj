(ns hylyht.core
  (:use compojure.core)
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [resources not-found]]
            [compojure.handler :refer [site]]))
            ;[hylyht.login :refer [authenticate-user]]))

(defroutes app-routes

  (GET "/" [] "<p>Hello from hylyht</p>")
  ;(POST "/login" [email password] (authenticate-user email password))
  (resources "/")
  (not-found "Page not found"))

(def handler
  (site app-routes))
