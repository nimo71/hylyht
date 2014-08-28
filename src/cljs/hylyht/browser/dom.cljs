(ns hylyht.browser.dom
  (:require [hylyht.markup :as m]))

(defprotocol Node)


; http://www.html5rocks.com/en/tutorials/internals/howbrowserswork/#The_browser_main_functionality
(defrecord DomNode [markup peer children]

  ;(attach) attach the peer to the DomNode.

  ;(render) render the peer in the browser, consider style here. Applied after stylesheet rules.

  ;(layout) layout the peer in the browser, consider positioning here. Applied after stylesheet rules.
  )

; render root in the document
; create a root element and iterate over its children doing the same
; for each element created create a html element containing a peer js/element
(defn render [root-markup])

(defn attach [markup]
  (if (string? markup)
    (DomNode. markup markup)

    (let [tagname  (name (:tag-name markup))
          helper   (goog.dom.DomHelper.)
          el       (.createDom helper tagname)
          children (map attach (:children markup))]
      (DomNode. markup el children))))

