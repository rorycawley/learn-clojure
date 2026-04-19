# Definition of Done

A piece of work is done when it is **small, clear, verified, and banked**.

This project uses a **single-focus workflow**.  
That means “done” does not mean “everything is finished”.  
It means **one coherent piece of work** has been completed properly.

---

## The 7 checks

A change is done when all 7 checks are true:

### 1. The change does one thing
The scope is single and clear.

Examples:

- add one function
- fix one bug
- rename one concept
- add one behaviour test
- refactor one small area

If the work contains multiple unrelated changes, it is not done.  
It is too big.

---

### 2. The behaviour was explored in the REPL
Before or during implementation, the behaviour was checked in the REPL.

This means the developer has:

- tried the function with sample data
- observed inputs and outputs
- confirmed the data shape
- reduced uncertainty before committing

The REPL is the place where understanding happens.

---

### 3. The code is clear enough
The code for the change now exists in the codebase and is understandable.

That means:

- naming is clear enough
- the change is as small as reasonably possible
- the code matches the intended behaviour
- unnecessary complexity was not added

Done does not mean perfect.  
It means clear enough to keep.

---

### 4. Tests cover the behaviour
The change is covered by automated tests where appropriate.

That means:

- new behaviour has tests
- changed behaviour has updated tests
- broken expectations are corrected in tests
- tests focus on behaviour, not trivia

The REPL proves it once.  
The tests prove it repeatedly.

---

### 5. `bb app:precommit` passes
The standard quality gate succeeds.

```bash
bb app:precommit
```

This runs lint, formatting check, and tests. If any step fails, fix the issue before continuing — do not skip the gate.

---

### 6. The diff has been reviewed
Before committing, the change was inspected as a whole.

```bash
bb git:diff
bb git:status
```

Review catches accidental edits, leftover debug code, and anything that does not belong in this single change.

---

### 7. The change is committed
The work is banked in Git.

```bash
git add .
git commit -m "..."
```

A commit means the work is **banked**.

Once committed, the change is safely stored and does not need to be mentally carried.

---

## Short version

A change is done when:

- the change does one thing
- the behaviour was explored in the REPL
- the code is clear enough
- tests cover the behaviour
- `bb app:precommit` passes
- the diff has been reviewed
- the change is committed

---

## Done checklist

Use this at the end of every loop.

- [ ] The change does one thing
- [ ] The behaviour was explored in the REPL
- [ ] The code is clear enough
- [ ] Tests cover the behaviour
- [ ] `bb app:precommit` passes
- [ ] The diff has been reviewed
- [ ] The change is committed

---

## Not done yet if…

The work is **not done** if any of these are true:

- the change still mixes multiple concerns
- the behaviour is still uncertain
- the code “sort of works” but is not understood
- tests are missing for important behaviour
- lint, format, or tests are failing
- the work is only in the working tree and not committed

---

## Philosophy

This repo is developed by following a repeatable system:

```text
Pick 1 thing -> REPL -> Code -> Test -> Precommit -> Review -> Commit
```

Done means:

**understood, implemented, verified, and banked**

That is enough.
