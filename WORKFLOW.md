# WORKFLOW.md

## Purpose

This file defines the standard development workflow for this repo.

The goal is to make development **repeatable, calm, and mechanical**.

The workflow is designed for deep focus on **one single thing at a time**:

- one change
- one loop
- one branch
- one verified commit

A commit is **banked work**.  
Once it is tested, checked, and committed, it is safely stored.

---

## Why This Workflow Exists

### 1. To reduce mental overload

Working on multiple things at once creates drift, context switching, and half-finished work.

This workflow forces the work into a small loop so that progress is always visible.

### 2. To use the REPL properly

The REPL is the fastest way to explore, probe, and understand code.
It should be used before large edits and during development, not as an afterthought.

### 3. To turn tests into evidence

Tests are not decoration.
They are evidence that the behaviour works and stays working.

### 4. To make git feel safe

Git is not just source control.
Git is the place where verified work is banked.

Uncommitted work is still fluid.
Committed work is saved.

---

## The Core Operating Model

### The rule: one thing at a time

At any moment, work on exactly one clearly stated change.

Use this sentence before starting:

> I am changing **one behaviour**.  
> I will know I am done when **one specific outcome** is true.

Examples:

- "I am changing the account creation validation so blank names are rejected."
- "I am changing the projector so it stores the current balance."
- "I am changing the HTTP handler so it returns 404 for missing accounts."

If the work starts branching into several things, stop and split it.

---

## The Workflow Loop

### Visual loop

```text
+-------------------+
|  Choose one thing |
+---------+---------+
          |
          v
+-------------------+
|  Probe in REPL    |
|  Understand       |
+---------+---------+
          |
          v
+-------------------+
|  Write/adjust     |
|  test             |
+---------+---------+
          |
          v
+-------------------+
|  Write minimum    |
|  code             |
+---------+---------+
          |
          v
+-------------------+
|  Re-check in REPL |
|  Observe result   |
+---------+---------+
          |
          v
+-------------------+
|  Run tests/checks |
+---------+---------+
          |
          v
+-------------------+
|  Commit = bank it |
+---------+---------+
          |
          v
+-------------------+
| Next single thing |
+-------------------+
```

### Loop summary

**Choose → Probe → Test → Code → Verify → Bank**

That is the whole system.

---

## Standard Commands

The repo should expose a small, stable command surface through `bb`.

The exact underlying tool may change (`clj`, `lein`, etc.), but the outer workflow should stay the same.

### Standard task names

```bash
bb app:repl
bb app:run
bb app:test
bb app:lint
bb app:fmt:check
bb app:fmt:fix
bb app:precommit
```

### Why

This keeps the workflow consistent across time.
You should not have to remember different low-level commands for each repo.

### Note

The sample `bb.edn` already includes most of these.
Add `app:repl` if it does not exist yet.

Example:

```clojure
app:repl {:doc "Start REPL"
          :task (sh "clj" "-M:dev")}
```

If this repo is still using Leiningen internally, the wrapper can call `lein repl` instead.
The important thing is the **workflow contract**, not the underlying command.

---

## Start-of-Session Workflow

## Why

The start of a session should reduce ambiguity.
Do not begin by wandering.

## What

Before writing code, establish:

- what single thing you are changing
- where you will explore it
- what "done" means

## How

### 1. Sync the repo

```bash
git switch main
git pull --ff-only
```

### 2. Create a short-lived branch for one change

```bash
git switch -c feat/single-clear-change
```

Branch names should describe one thing only.

Examples:

```text
feat/reject-blank-account-name
fix/return-404-for-missing-account
refactor/extract-balance-fold
test/add-projector-coverage
```

### 3. Write the change statement

In your notes, issue, or scratchpad, write:

- **Change:** one sentence
- **Done means:** one sentence

Example:

- Change: return 404 when account is missing
- Done means: handler test proves missing account gets 404

### 4. Start the REPL

```bash
bb app:repl
```

---

## Development Workflow: The Main Loop

## Step 1: Probe in the REPL

### Why

Before changing code, understand the current behaviour in the smallest possible way.

### What

Use the REPL to:

- call the function directly
- inspect inputs and outputs
- reduce the problem to a small example
- explore the domain shape

### How

Examples:

- load the namespace
- call the function with sample data
- reduce large input data to the smallest useful case
- try possible transformations until the behaviour is clear

The REPL is for **understanding**.

Do not start by editing five files if you do not yet understand the shape of the problem.

---

## Step 2: Write or adjust a test

### Why

The test captures the intended behaviour before or during the code change.

### What

Write the smallest test that expresses the behaviour being changed.

### How

Good tests are:

- small
- behaviour-focused
- easy to read
- close to the language of the domain

Ask:

- What should happen?
- What should not happen?
- What is the smallest test that proves it?

If the change is exploratory, you may use the REPL first and write the test once the behaviour becomes clear.

---

## Step 3: Write the minimum code

### Why

Smaller changes are easier to understand, verify, and commit.

### What

Write only enough code to satisfy the current behaviour.

### How

Prefer:

- small edits
- local changes
- clear names
- simple data transformations

Avoid:

- speculative abstractions
- unrelated cleanup
- mixing refactor + feature + rename + style changes in one pass

One commit should tell one story.

---

## Step 4: Re-check in the REPL

### Why

The REPL gives immediate feedback and helps you observe what changed.

### What

Re-run the current code in the REPL after the edit.

### How

Use the REPL to confirm:

- the function now behaves as expected
- the data shape is correct
- the transformation is understandable
- the next edit is actually necessary

This is the **observe** part of the loop.

---

## Step 5: Run focused verification

### Why

You need fast evidence while still in flow.

### What

Run the smallest meaningful verification first.

### How

Usually:

1. run the relevant test namespace or test
2. run all tests
3. run formatting/linting checks
4. run the full precommit task

At minimum before commit:

```bash
bb app:test
bb app:precommit
```

If formatting is needed:

```bash
bb app:fmt:fix
```

---

## The Commit Rule

A commit is a banked checkpoint.

Do not commit random motion.
Commit verified progress.

### Commit only when all of these are true

- the change is about one thing
- the code is understandable
- the relevant tests pass
- repo checks pass
- you have read the diff
- the commit message says what changed

### Before every commit

```bash
git status
git diff
bb app:precommit
```

### Stage carefully

Prefer:

```bash
git add -p
```

This helps keep unrelated edits out of the commit.

### Commit message format

Use clear, behavioural messages.

Examples:

```text
feat(account): reject blank account name
fix(api): return 404 for missing account
refactor(balance): extract fold from handler
test(projector): cover account-closed case
```

---

## Git Workflow

## Why

Git should support the development loop, not disrupt it.

## What

Use short-lived branches and small verified commits.

## How

### 1. Start from updated main

```bash
git switch main
git pull --ff-only
git switch -c feat/single-clear-change
```

### 2. Work in small verified loops

Repeat:

- REPL
- test
- code
- verify

### 3. Bank progress with a commit

```bash
git add -p
git commit -m "feat(account): reject blank account name"
```

### 4. Push the branch

```bash
git push -u origin feat/single-clear-change
```

### 5. Merge only verified work

Preferred: open a PR, review your own diff carefully, then merge.

If working solo and merging directly, still do a self-review first.

### 6. After merge

```bash
git switch main
git pull --ff-only
git branch -d feat/single-clear-change
```

---

## WIP Rule

If you are interrupted and the work is not yet bankable:

- do **not** pretend it is done
- either keep it local
- or make a clearly marked temporary commit on the branch

Example:

```text
wip: reduce failing projector case to minimal example
```

Rules for WIP commits:

- never merge them as-is
- replace or squash them later
- use them only to save interrupted work

A WIP commit is **shelving**, not banking.

---

## End-of-Session Workflow

## Why

A good ending makes the next start easier.

## What

End with either:

- a clean banked commit, or
- a clear restart note

## How

### Best case: end on green

- tests passing
- precommit passing
- commit created
- branch pushed

### If not finished

Leave a restart note in plain language:

- what is true now
- what is broken now
- what the next smallest step is

Example:

```text
Current state:
- handler path isolated
- missing-account case reproduced
- test written and failing

Next step:
- fix lookup result handling in api namespace
```

The next session should begin from that note, not from memory.

---

## Stuck Protocol

When stuck, do not thrash.

Use this order:

### 1. Go back to the REPL

Reduce the problem to the smallest possible example.

### 2. Shrink the change

Ask:

- Can I make this problem smaller?
- Can I isolate one function?
- Can I test one behaviour only?

### 3. Separate understanding from implementation

If confused, stop editing and just explore.

### 4. Write one tiny failing test

A tiny failing test often reveals what the real problem is.

### 5. Avoid side quests

Do not rename unrelated things.
Do not "clean up while I'm here".
Do not switch to another task.

Return to the loop.

---

## Workflow Rules for This Repo

These are the standing rules:

1. Work on one thing at a time.
2. Start with understanding, usually in the REPL.
3. Capture behaviour with tests.
4. Write the minimum code needed.
5. Verify before committing.
6. Commit only bankable work.
7. Keep branches short-lived.
8. End each session with either a green commit or a restart note.

---

## The Daily Mechanical Loop

Use this exact mental model:

### Before work
- choose one thing

### During work
- REPL
- test
- code
- verify

### Before commit
- diff
- precommit
- self-review

### After verification
- commit
- push
- merge

### Then
- choose the next one thing

---

## Short Version

If in doubt, do this:

```text
1. Choose one behaviour
2. Start REPL
3. Reproduce / explore
4. Write test
5. Write minimum code
6. Re-check in REPL
7. Run bb app:precommit
8. Read git diff
9. Commit = bank it
10. Repeat
```

---

## Optional Improvement to `bb.edn`

To support this workflow well, make sure the repo exposes at least these tasks:

```clojure
{:tasks
 {:init ...
  app:repl {:doc "Start REPL"
            :task (sh "clj" "-M:dev")}
  app:run {:doc "Start application"
           :task (sh "clj" "-M:run")}
  app:test {:doc "Run all tests"
            :task (sh "clj" "-M:test")}
  app:lint {:doc "Lint with clj-kondo"
            :task (sh "clj-kondo" "--parallel" "--lint" "src:test:dev")}
  app:fmt:check {:doc "Check code format"
                 :task (sh "cljfmt" "check")}
  app:fmt:fix {:doc "Fix code format"
               :task (sh "cljfmt" "fix")}
  app:precommit {:doc "Run all checks and tests"
                 :depends [app:lint app:fmt:check app:test]}}}
```

The exact internals can vary.
The workflow should not.

---

## Final Principle

Do not try to win by speed.

Win by running the loop consistently:

**Choose → Probe → Test → Code → Verify → Bank**
