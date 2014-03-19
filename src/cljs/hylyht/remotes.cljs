(ns hylyht.remotes
  (:require [hylyht.core :refer [handler]]
            [hylyht.login.java.validators :as v]
            [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]))

(defremote email-domain-errors [email]
  (v/email-domain-errors email))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
