(require '[clojure.edn :as edn] '[clojure.java.io :as io]
         '[kotoba.lang.text :as str] '[clojure.test :as t])
(def contracts (edn/read-string (slurp "repository-contracts.edn")))
(doseq [p (:required contracts)] (assert (.isFile (io/file p)) (str "missing " p)))
(doseq [f (file-seq (io/file ".")) :when (and (.isFile f) (str/ends-with? (.getName f) ".edn")
                                               (not (str/includes? (.getPath f) "/.git/")))]
  (edn/read-string (slurp f)))
(let [bad (for [f (file-seq (io/file ".")) :when (and (.isFile f)
                 (not (str/includes? (.getPath f) "/.git/"))
                 (some #(str/ends-with? (.getName f) %) (:forbidden-extensions contracts)))] f)]
  (assert (empty? bad) (str "forbidden artifacts " bad)))
(def nss '[mimamori.murakumo-test mimamori.social-publication-test
           mimamori.tests.test-bond mimamori.tests.test-cell mimamori.tests.test-coverage
           mimamori.tests.test-kotoba-autorun mimamori.tests.test-ontology-parity
           mimamori.tests.test-shakai mimamori.tests.test-wasm-app])
(doseq [n nss] (require n))
(let [r (apply t/run-tests nss)] (System/exit (if (zero? (+ (:fail r) (:error r))) 0 1)))
