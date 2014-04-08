(defproject hylyht "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.2.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [compojure "1.1.6"]]

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.8"]
            [com.keminglabs/cljx "0.3.2"]]

  :ring {:handler hylyht.core/handler}

  :source-paths ["src/clj" "target/classes"]

  :test-paths ["test/clj" "target/classes"] ; "target/test-classes"]

  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}

                  {:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :cljs}

                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :clj}

                  {:source-paths ["test/cljx"]
                   :output-path "target/test-classes"
                   :rules :cljs}]}

  :cljsbuild {:builds [{:source-paths ["src/cljs" "target/classes"] ;["src/cljs"]

                        ;; Google Closure (CLS) options configuration
                        :compiler {:output-to "resources/public/js/hylyht.js"
                                   :optimizations :whitespace
                                   :pretty-print true }}]}



  :hooks [cljx.hooks])

;  :aliases { build-run ["do" "clean," "cljsbuild" "clean," "cljx" "once," "cljsbuild" "once," "ring" "server-headless"]})
