# Developer Workflow

This project is developed using a **patient, single-focus, REPL-first workflow**.

The goal is not to rush.  
The goal is to work as a **clear, repeatable system**.

A good session in this repo looks like this:

1. choose **one single thing**
2. explore it in the REPL
3. write or change code
4. write or change tests
5. run checks
6. review the diff
7. commit it to Git
8. treat the commit as **banked work**

---

## 1. Core idea

This workflow is designed around a few rules.

### Rule 1: One thing at a time
Only work on **one small change** at once.

Examples:

- add one function
- change one behaviour
- fix one bug
- rename one concept
- add one test case

Do not mix multiple concerns in one loop.

Why:

- it reduces cognitive load
- it makes the REPL session easier to follow
- it makes tests easier to understand
- it makes commits meaningful
- it makes rollback easy

### Rule 2: Use the REPL to understand
The REPL is where you **observe**, **probe**, **try**, and **verify**.

Use it to:

- call functions with sample data
- inspect shapes of data
- try transformations
- check assumptions
- load changed namespaces
- get clear before writing more code

The REPL is not a side activity.  
It is the main thinking tool.

### Rule 3: Tests make the change durable
A working REPL experiment is good.  
A test makes that behaviour **repeatable**.

Use tests to lock in what you now know to be true.

### Rule 4: Git is the bank
A commit is not just storage.  
A commit is **banked progress**.

Once a change is understood, tested, checked, and committed, it is safe.  
You do not need to mentally carry it anymore.

---

## 2. Workflow shape

There are really only three modes of work in this repo:

1. **Explore**
2. **Implement**
3. **Bank**

That is the whole system.

---

## 3. The workflow loop

```text
┌─────────────┐
│ Pick 1 thing│
└──────┬──────┘
       │
       v
┌─────────────┐
│ Start REPL  │
│ bb app:repl │
└──────┬──────┘
       │
       v
┌──────────────────────┐
│ Explore the behaviour│
│ and data patiently   │
└──────┬───────────────┘
       │
       v
┌──────────────────────┐
│ Write/change code    │
│ in small steps       │
└──────┬───────────────┘
       │
       v
┌──────────────────────┐
│ Write/change tests   │
│ to lock behaviour in │
└──────┬───────────────┘
       │
       v
┌──────────────────────┐
│ Run checks           │
│ bb app:precommit     │
└──────┬───────────────┘
       │
       v
┌──────────────────────┐
│ Review the diff      │
│ bb git:diff          │
└──────┬───────────────┘
       │
       v
┌──────────────────────┐
│ Commit to Git        │
│ = bank the work      │
└──────┬───────────────┘
       │
       v
┌─────────────┐
│ Next 1 thing│
└─────────────┘
```

---

## 4. Standard session workflow

### 4.1 Start of session

#### What

Prepare the environment and reduce friction.

#### Why

A smooth start makes it easier to stay focused.

#### How

```bash
bb app:deps
bb git:branch
bb git:status
bb app:repl
```

#### Notes

- `bb app:deps` warms dependencies and classpath caches
- `bb git:branch` confirms where you are
- `bb git:status` confirms your working tree state
- `bb app:repl` starts the REPL with dev + test paths

---

### 4.2 Pick the one thing

#### What

Define a **single small target** for the loop.

#### Why

ADHD does better with a tight target than with an open field.

#### How

Write a one-line statement before you begin.

Examples:

- `Add money parser for string input`
- `Fix interest calculation rounding`
- `Rename account status predicate`
- `Add test for overdraft rejection`

If the statement contains `and`, it is probably too big.

Bad:

- `Fix transfer logic and improve naming and clean tests`

Good:

- `Fix transfer rejection when balance is insufficient`

---

### 4.3 Explore in the REPL

#### What

Use the REPL to understand the current behaviour and try the next behaviour.

#### Why

Clojure is easiest to learn and develop when feedback is immediate.

#### How

Typical REPL actions:

- require or reload namespaces
- call functions with sample data
- inspect return values
- check data shapes
- experiment before committing to code structure

Typical pattern:

```clojure
(require 'your.namespace :reload)
(comment
  ;; try examples here
  )
```

#### REPL questions to ask

- What goes in?
- What comes out?
- Is the data shape what I expected?
- What is the smallest function that would make this easier?
- Can I make the behaviour visible with one simple test?

Do not stay in the REPL forever.
The REPL is for learning and checking.
Once you understand the move, write it into code and tests.

---

### 4.4 Change code in small steps

#### What

Make the smallest code change that moves the behaviour forward.

#### Why

Small changes are easier to reason about, test, lint, and commit.

#### How

Preferred approach:

1. change one function or one tiny set of related functions
2. keep names clear
3. keep data shapes simple
4. avoid broad refactors unless that is the single goal of the loop

#### Guideline

Prefer several small loops over one heroic change.

---

### 4.5 Write or update tests

#### What

Turn the behaviour you just proved in the REPL into an automated test.

#### Why

The REPL proves it once.
The test proves it forever.

#### How

Run tests with:

```bash
bb app:test
```

Use tests to describe behaviour, not implementation detail.

Good test focus:

- given input X, result is Y
- when invalid input is supplied, the function rejects it
- when event A happens, state B results

Avoid testing internal trivia unless it matters to behaviour.

---

### 4.6 Run quality checks

#### What

Run the standard checks before committing.

#### Why

This creates a consistent quality gate and reduces regressions.

#### How

For quick manual checks:

```bash
bb app:lint
bb app:fmt:check
bb app:test
```

Standard combined check:

```bash
bb app:precommit
```

This is the main checkpoint command.

`bb app:precommit` runs:

- lint
- formatting check
- tests

If it fails, fix the issue before committing.

If formatting is the issue:

```bash
bb app:fmt:fix
bb app:fmt:check
```

---

## 5. Git workflow

This repo uses a **simple banking workflow**.

The idea is:

- do one small piece of work
- verify it
- commit it
- treat that commit as safely stored

For a solo developer, this should stay simple.

---

### 5.1 Branch workflow

Use **short-lived branches** for each small change.

#### Why

This gives you isolation and clarity without much overhead.

#### Branch naming

Use names that describe the change.

Examples:

- `feat/add-money-parser`
- `fix/rounding-in-interest`
- `refactor/rename-account-predicates`
- `test/add-overdraft-cases`

Check branch:

```bash
bb git:branch
```

View status:

```bash
bb git:status
```

View change size:

```bash
bb git:diff
```

---

### 5.2 Commit workflow

A commit should represent **one coherent completed step**.

#### Commit when all are true

- the change does one thing
- the behaviour was explored in the REPL
- the code is clear enough
- tests cover the behaviour
- `bb app:precommit` passes
- the diff has been reviewed

#### Commit message style

Use a short imperative subject.

Examples:

- `Add money parser for string amounts`
- `Fix overdraft validation`
- `Rename transaction status helpers`
- `Add tests for account closure rules`

A commit message should answer:

**What did I bank?**

---

### 5.3 Banking rule

After a good commit:

- stop carrying that work mentally
- trust the system
- move on to the next small thing

A commit is a checkpoint.
It is not just a backup.
It is a deliberate deposit.

---

### 5.4 Suggested Git sequence

```bash
git switch -c feat/add-money-parser
bb app:precommit
bb git:diff
bb git:status
git add .
git commit -m "Add money parser for string amounts"
```

If you use a remote:

```bash
git push -u origin feat/add-money-parser
```

---

## 6. Daily working modes

A session usually falls into one of these modes.

### 6.1 Learning mode

Use when the problem is not yet clear.

Focus:

- REPL exploration
- reading code
- tracing data
- tiny experiments

Goal:

- understand the next move

Typical commands:

```bash
bb app:repl
bb git:status
```

### 6.2 Building mode

Use when the change is understood.

Focus:

- implement one behaviour
- add tests
- keep scope tight

Typical commands:

```bash
bb app:test
bb app:lint
bb app:fmt:check
```

### 6.3 Banking mode

Use when the change is ready to be stored.

Focus:

- run the full checks
- review the diff
- commit cleanly

Typical commands:

```bash
bb app:precommit
bb git:diff
bb git:status
git add .
git commit -m "..."
```

---

## 7. Recovery workflow when overwhelmed

When the mind gets noisy, do not try to solve everything.

Return to the system.

### Recovery steps

1. stop
2. check status
3. name the one thing
4. start or return to the REPL
5. make one tiny move

Commands:

```bash
bb git:status
bb git:diff
bb app:repl
```

Then ask:

- What exactly am I changing?
- What is the smallest possible step?
- Can I prove it in the REPL first?
- Can I write one test for it?

If still overwhelmed, reduce scope again.

Examples:

Instead of:

- `implement transfers`

Reduce to:

- `write a function that subtracts an amount from a balance`
- `write one test for insufficient funds`
- `load namespace and try three examples in REPL`

---

## 8. Definition of done for one loop

A loop is done when:

- the change does one thing
- the behaviour was explored in the REPL
- the code is clear enough
- tests cover the behaviour
- `bb app:precommit` passes
- the diff has been reviewed
- the change is committed

That is enough.

Do not keep polishing once the loop is done.
Bank it.

---

## 9. Command reference

### Start / warm up

```bash
bb app:deps
bb app:repl
bb app:run
```

### Verification

```bash
bb app:test
bb app:lint
bb app:fmt:check
bb app:fmt:fix
bb app:precommit
bb app:antq
```

### Git awareness

```bash
bb git:status
bb git:diff
bb git:branch
```

---

## 10. Recommended working rhythm

For each piece of work:

1. **pick one thing**
2. **explore in REPL**
3. **change code**
4. **change tests**
5. **run `bb app:precommit`**
6. **review diff**
7. **commit**
8. **repeat**

In short:

```text
Pick 1 thing -> REPL -> Code -> Test -> Precommit -> Review -> Commit
```

That is the main loop.

---

## 11. Practical example

Example: adding a parser for money values.

### Step 1

Choose the one thing:

```text
Add parser for string money amounts
```

### Step 2

Start REPL:

```bash
bb app:repl
```

### Step 3

Try examples manually in the REPL.

- valid input
- invalid input
- edge cases

### Step 4

Implement the smallest function.

### Step 5

Add tests for:

- valid amount
- invalid amount
- empty string

### Step 6

Run checks:

```bash
bb app:precommit
```

### Step 7

Inspect changes:

```bash
bb git:diff
bb git:status
```

### Step 8

Commit:

```bash
git add .
git commit -m "Add parser for string money amounts"
```

Now it is banked.

---

## 12. Final principle

Do not try to be fast.
Do not try to hold the whole system in your head.
Do not try to finish everything.

Follow the loop.

One thing.
Patiently explored.
Clearly coded.
Tested.
Banked.
