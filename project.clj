(defproject ynclicker "0.1.0-SNAPSHOT"
  :description "Yes No clicker backend app"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [metosin/compojure-api "1.1.8"]
                 [alandipert/enduro "1.2.0"]]
  :min-lein-version "2.6.1"
  :ring {:handler ynclicker.handler/app}
  :uberjar-name "ynclicker.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [cider/cider-nrepl "0.14.0-SNAPSHOT"]]
                   :plugins [[ikitommi/lein-ring "0.9.8-FIX"]]
                   :ring {:handler ynclicker.handler/app
                          :nrepl {:start? true}}
                   :repl-options {:nrepl-middleware
                                  [cider.nrepl.middleware.apropos/wrap-apropos
                                   cider.nrepl.middleware.classpath/wrap-classpath
                                   cider.nrepl.middleware.complete/wrap-complete
                                   cider.nrepl.middleware.info/wrap-info
                                   cider.nrepl.middleware.inspect/wrap-inspect
                                   cider.nrepl.middleware.macroexpand/wrap-macroexpand
                                   cider.nrepl.middleware.ns/wrap-ns
                                   cider.nrepl.middleware.resource/wrap-resource
                                   cider.nrepl.middleware.stacktrace/wrap-stacktrace
                                   cider.nrepl.middleware.test/wrap-test
                                   cider.nrepl.middleware.trace/wrap-trace
                                   cider.nrepl.middleware.undef/wrap-undef]}}})
