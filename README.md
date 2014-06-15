# hylyht

A Clojure library designed to ... well, that part is up to you.

## Usage

FIXME

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

## TODO:
- Migrate all of the markup/HTML functionality over to protocol implementations
- test for cljs as well as clj (lookup testing cljx)
- Add event handling
- listen for all types of events
-- is history only causing navigation events to be raised
--- other types of events
---- submit form events
---- focus events
---- custom events e.g. validation?
- event handling using core.asynch
- make remote calls using json or clojure alternative? (maps?)
- create renderable protocol
- create component protocol
- create event-handler protocol
- handle dom within page
-- add, remove component
- pass events down into page
- pass events into components
- composite components
- raising events in components
- include styles in component
-- remote stylesheets
- create the whole page using cljs, the page could be represented as a monadic value and the transformations the monadic functions.
- page as value, would be good if it appeared to be immutable
