(ns hylyht.login
  (:require [hylyht.login.validators :refer [user-credential-errors]]
            [hylyht.login.java.validators :refer [email-domain-errors]]))

(declare validate-email validate-password)

(defn authenticate-user [email password]
  (if (or (boolean (user-credential-errors email password)) (boolean (email-domain-errors email)))
    (str "Please complete the form")
    (str email " and " password
           " passed the formal validation, but you still have to be authenticated")))
