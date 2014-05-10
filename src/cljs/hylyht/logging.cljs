(ns hylyht.logging
  (:require [goog.debug.Console :as console]
            [goog.debug.FancyWindow :as fancy]
            [goog.debug.Logger :as logger]))

(defprotocol ILogViewer
  (start-display [this] "Start displaying log messages in this viewer.")
  (stop-display [this] "Stop displaying log messages in this viewer."))

(def levels {:severe goog.debug.Logger.Level.SEVERE
             :warning goog.debug.Logger.Level.WARNING
             :info goog.debug.Logger.Level.INFO
             :config goog.debug.Logger.Level.CONFIG
             :fine goog.debug.Logger.Level.FINE
             :finer goog.debug.Logger.Level.FINER
             :finest goog.debug.Logger.Level.FINEST})

(defn get-logger [name]
  (goog.debug.Logger/getLogger name))

(defn severe [logger s]
  (.severe logger s))

(defn warning [logger s]
  (.warning logger s))

(defn info [logger s]
  (.info logger s))

(defn config [logger s]
  (.config logger s))

(defn fine [logger s]
  (.fine logger s))

(defn finer [logger s]
  (.finer logger s))

(defn finest [logger s]
  (.finest logger s))

(defn set-level [logger level]
  (.setLevel logger (get levels level goog.debug.Logger.Level.INFO)))

(extend-protocol ILogViewer

  goog.debug.Console
  (start-display [this]
    (.setCapturing this true))
  (stop-display [this]
    (.setCapturing this false))

  goog.debug.FancyWindow
  (start-display [this]
    (doto this
      (.setEnabled true)
      (.init ())))
  (stop-display [this]
    (.setCapturing this false)))

(defn console-output []
  (goog.debug.Console.))

(defn fancy-output [name]
  (goog.debug.FancyWindow. name))
