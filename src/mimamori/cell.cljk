(ns mimamori.cell
  "mimamori cell entry — kotodama-cell-runner contract (ADR-2605192415 §7.1).

  Registered by the superproject cell registry as
  MimamoriHeartbeatCell (node benjamin, cron 23 * * * *, healthz 13080).
  `fire` runs ONE deterministic heartbeat (ADR-2606112300 / pattern 2606091000):

      replay synthetic seed → §D4 offer-matching → keeper-side social-capital
      mint (moyai reuse) → aggregate coverage → ONE content-addressed tx
      appended to the actor-local kotoba commit-DAG → chain verified.

  NO external I/O — the live legs (real roster / §1.16 outreach / musubi
  ceremony / live social-capital mint) remain G7-gated. The returned summary is
  aggregate-only (G5): counts and CIDs, never a DID."
  (:require [mimamori.methods.autorun :as autorun]
            [mimamori.methods.bond :as bond]
            #?(:clj [clojure.java.io :as io])))

#?(:clj
   (defn- actor-dir
     "Standalone actor repository root."
     []
     (io/file (System/getProperty "user.dir"))))

#?(:clj
   (def log-default
     (delay (io/file (actor-dir) "data" "mimamori.datoms.kotoba.edn"))))

#?(:clj
   (defn fire
     "One heartbeat. Idempotent per log state (cycle derives from log length)."
     ([] (fire nil))
     ([log-path]
      (let [seed (bond/load-seed-file (io/file (actor-dir) "data" "seed-mimamori-bonds.edn"))
            target (or log-path @log-default)
            summary (autorun/run-cycle seed target)]
        (println (str "MimamoriHeartbeatCell cycle " (:cycle summary) ": "
                      "unkept " (:unkept-before summary)
                      "→" (get-in summary [:coverage :unkept-count])
                      " via " (:offers-emitted summary) " offers, "
                      (get-in summary [:shakai :minted-units]) " social-capital minted, "
                      "chain " (:chain-length summary) " ok → "
                      (subs (:cid summary) 0 16) "…"))
        summary))))
