(ns hylyht.browser.history
  (:require [goog.events :as events]
            [goog.History :as history]
            [goog.history.Html5History :as history5]))

(def ON_NAVIGATE goog.history.EventType.NAVIGATE)

(defn history [callback]
  (let [h (if history5/isSupported
            (goog.history.Html5History.)
            (goog.History.))]

    (events/listen h ON_NAVIGATE
      (fn [e]
        (callback {:token (keyword (.-token e))
                   :type (.-type e)
                   :navigation? (.-isNavigation e)})))

    (set-enabled h true)
    h))

(defn dispose-internal [h]
  (.disposeInternal h))

(defn get-token [h]
  (.getToken h))

(defn replace-token [h token opt-title]
  (.replaceToken h token opt-title))

(defn set-enabled [h enable]
  (.setEnabled h enable))

(defn set-token [h token]
  (.setToken h token))
