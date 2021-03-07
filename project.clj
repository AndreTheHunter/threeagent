(def three-version "0.126.1")
(def figwheel-version "0.5.20")
(defproject doughamil/threeagent "0.0.7-SNAPSHOT"
  :description "Build Three.js apps in a reagent-like fashion"
  :url "https://github.com/DougHamil/threeagent"
  :license {:name "MIT"}

  :deploy-repositories [["releases" {:url "https://clojars.org/repo/"
                                     :signing {:gpg-key "C89350FC"}
                                     :username :env
                                     :password :env}]
                        ["snapshots" {:url "https://clojars.org/repo/"
                                      :signing {:gpg-key "C89350FC"}
                                      :username :env
                                      :password :env}]]

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [medley "1.3.0"]
                 [reagent "1.0.0"]
                 [karma-reporter "3.1.0"]]


  :source-paths ["src/main"]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.10"]
            [lein-figwheel ~figwheel-version]]

  :profiles {:dev {:dependencies [[org.clojure/clojurescript "1.10.773"]
                                  [cider/cider-nrepl "0.25.9"]
                                  [cider/piggieback "0.5.2"]
                                  [figwheel ~figwheel-version]
                                  [doo "0.1.11"]
                                  [karma-reporter "3.1.0"]]}
             :test {:dependencies [[thheller/shadow-cljs "2.11.20"]]
                    :source-paths ["src/main" "src/test"]}}

  :npm {:dependencies [[three ~three-version]]}

  :doo {:paths {:karma "node_modules/.bin/karma"}}

  :figwheel {:http-server-root "public"
             :nrepl-port 7889
             :nrepl-middleware [cider.nrepl/cider-middleware]}

  :cljsbuild
  {:builds [{:id "render-test"
             :source-paths ["src/render_test"]
             :watch-paths ["src/main" "src/render_test"]
             :figwheel {:on-jsload "threeagent.render-test.core/on-js-reload"}
             :compiler {:optimizations :none
                        :infer-externs true
                        :main threeagent.render-test.core
                        :output-dir "tests/render_test/js/out"
                        :output-to "tests/render_test/js/main.js"
                        :asset-path "js/out"
                        :aot-cache false
                        :install-deps true
                        :npm-deps {:three ~three-version}}}
            ;;FIXME compiler error:
            ;; ERROR: JSC_LANGUAGE_FEATURE. This language feature is only supported for ECMASCRIPT8 mode or better: async function. at /Users/andre/Projects/threeagent/node_modules/three/build/three.module.js line 21991 : 19
            ;; ERROR: JSC_LANGUAGE_FEATURE. This language feature is only supported for ECMASCRIPT8 mode or better: async function. at /Users/andre/Projects/threeagent/node_modules/three/build/three.module.js line 22010 : 4
            ;; ERROR: JSC_LANGUAGE_FEATURE. This language feature is only supported for ECMASCRIPT8 mode or better: async function. at /Users/andre/Projects/threeagent/node_modules/three/build/three.module.js line 22027 : 20
            {:id "dev"
             :source-paths ["src/main" "src/dev"]
             :figwheel {:on-jsload "threeagent.dev.core/on-js-reload"}
             :compiler {:optimizations :none
                        :infer-externs true
                        :main threeagent.dev.core
                        :output-dir "resources/public/js/out"
                        :output-to "resources/public/js/main.js"
                        :asset-path "js/out"
                        :aot-cache false
                        :install-deps true
                        :npm-deps {:three ~three-version}}}]})
                        

                        
