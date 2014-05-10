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


; add to history
; change url in navigation bar without page request
; back event

; core.asynch rather than callbacks...

; User navigates to index and app is loaded into the browser, with http://hylyht.com/index.html
; displayed in the browser window.
;
; User enters details into the login form and clicks login.
;
; Profile page is displayed and the navigation bar displays http://hylyht.com/profile.html the
; page should not be refreshed, content should be loaded via AJAX.
;
; What about the hash bang, how does this fit into the no-refresh with the Google Closure history5 API?
;
;
