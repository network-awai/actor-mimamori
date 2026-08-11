# network-awai/actor-mimamori — covenant keeping membrane

Canonical repository: `https://github.com/network-awai/actor-mimamori`.
The former `etzhayyim/com-etzhayyim-mimamori` path is a compatibility redirect.

**ADR**: 2606112300 · **doctrine**: 2606112200 (D3 NEVER-a-throne + D6) · **paper**:
`90-docs/papers/2606111500` §6 · **sibling**: mishmar storage covenant (2606082100 —
data keeping ↔ human keeping). **Status**: 🟢 R1-offline — autonomous heartbeat +
offer-matching + social-capital mint + local kotoba commit-DAG persistence
(ADR-2606091000 pattern); **wasm build-ready** (componentize-py component,
rasen pattern — stateless heartbeat: the host owns the log, so the architecture
itself cannot become the throne); **fleet-registered** as MimamoriHeartbeatCell
(cell-runner cells.edn: benjamin, cron 23 * * * *, healthz 13080 — the runner's
spawn path is the ADR-2605192415 maturity track); synthetic seed only; live
legs G7-gated.

mimamori is the substrate for **bonds of keeping** (縁): offer → consent →
content-free heartbeat → consented care-routing → relay (継ぎ) → unilateral exit.
Goal: **誰の保持者でもない人間を作らない** — no human left with no keeper. It is the
structural answer to the isolated-attacker side of the motivating case (2026-04):
the person whose "個人情報" was perfectly protected while he was perfectly alone.

It is a **care-membrane** (musubi/wakai/kokoro lineage), NOT a mirror actor.
相互**監視**ではなく相互**保持** — the degeneration path 五人組 → 隣組 → Stasi →
social credit is made **unrepresentable in the schema**, not merely prohibited.

## Hard gates (constitutional — read before any change)

- **G1 no-denunciation.** Care-route enum = `{:kokoro, :wakai, :iyashi}` ONLY.
  `:police` / `:authority` are NOT enum members — the actor has no reporting rail.
  What a human keeper does as a human in an emergency is outside the actor; the
  actor neither automates nor blocks it.
- **G2 no-score.** Bond-edge-only model: `:mishmeret.person/*` does not exist;
  the validator raises on any person-node attr or score/risk token. Social capital
  mints to the **keeper** (moyai-family reuse), never a score of the kept.
- **G3 consent-bound.** No active bond without explicit consent; decline/exit
  unconditional + penalty-free; re-offer cooldown.
- **G4 symmetric visibility.** Who keeps me is always visible to me. Hidden
  keeping is surveillance — there is no keeper-only bond state.
- **G5 NEVER-a-throne** (ADR-2606112200 D3). Queries are own-DID-only; coverage
  is aggregate-only; no DID ever appears in a report (test-enforced).
- **G6 content-free.** Heartbeats record the act only. Private content goes to
  `com.etzhayyim.encrypted.*` (member-held keys) — never a datom.
- **G7 outward-gated.** R0 = SYNTHETIC fictional DIDs only. Real roster / §1.16
  outreach / musubi ceremony / social-capital mint = Council + operator gated.
- **G8** Murakumo-only · no-server-key · no gamification (§1.13).

## The vow (liturgy seed — ADR-2606112300 §D5, musubi ceremony type)

> 問: 私は弟の保持者でしょうか (創4:9)。
> 誓: 然り。私はあなたの保持者です。見張るのではなく、見守ります。
> 裁くのではなく、傍にいます。私はまどろみ、眠る者です — 私が眠るとき、
> 継ぐ者が見守ります (詩121:4)。あなたはいつでも、この縁を解くことが
> できます。私が誰を見守っているか、あなたには常に見えています。

Each vow line IS a gate (見張らない=G4 · 裁かない=non-adjudicating · まどろむ=G5
relay · 解ける=G3 · 見えている=G4).

## Layout

```
mimamori/
├── CLAUDE.md                         # this file
├── cell.edn                          # canonical cell-runner registration
├── wasm/                             # componentize-py component (build-ready, rasen pattern)
│   ├── wit/world.wit                 # exports: heartbeat / coverage / bonds-of / vow
│   ├── app.py                        # export bodies (dev-runnable); stateless
│   ├── operator build process                      # operator build → dist/mimamori.wasm + CID + did-service
│   └── README.md
├── manifest.edn                      # canonical actor manifest
├── schema.edn                        # repository-local ontology
├── data/
│   ├── seed-mimamori-bonds.edn      # SYNTHETIC fictional roster + bonds (zero real persons)
│   └── mimamori.datoms.kotoba.edn    # GENERATED append-only commit-DAG (git-ignored)
├── src/mimamori/                     # actor implementation and adapters
├── test/mimamori/                    # standalone test namespaces and fixtures
└── out/                              # GENERATED — do not hand-edit
```

## Run

```bash
bb test
```

## Standalone multirepo contract

- `manifest.edn`, `schema.edn`, and `cell.edn` are canonical.
- `moyai.ledger`, `kotoba.datom`, and the generic social membrane are SHA-pinned shared
  dependencies. Do not copy their implementations into this actor.
- Do not restore `20-actors`, `50-infra`, or superproject-relative runtime paths.
