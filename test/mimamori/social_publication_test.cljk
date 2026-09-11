(ns mimamori.social-publication-test
  (:require [clojure.test :refer [deftest is]]
            [mimamori.cells.social-post.state-machine :as cell]
            [mimamori.methods.social :as social]))

(deftest shared-social-gates
  (let [post (social/draft-observation-post "subject" "body" ["a" "b"])
        drafted (cell/transition-to-drafted {"subject" "x" "sources" ["a" "b"]})
        refused (cell/transition-to-drafted
                 {"subject" "x" "sources" ["a" "b"] "server_held_key" true})]
    (is (= ":dry-run" (get post ":post/status")))
    (is (false? (get post ":post/server-held-key")))
    (is (= cell/phase-drafted (get-in drafted ["cell_state" "phase"])))
    (is (= cell/phase-refused (get-in refused ["cell_state" "phase"])))
    (is (thrown? clojure.lang.ExceptionInfo (social/build-live {})))))
