# Commit Message Guide

A commit is **banked work**.

Each commit should capture **one small, complete, verified change**.

The commit message should state exactly what was banked.

---

## Rule

Write commit messages as a **short imperative sentence**.

Examples:

- `Add money parser for string amounts`
- `Fix overdraft validation`
- `Rename account status predicate`
- `Add tests for transfer rejection`
- `Refactor balance calculation`

This is the standard.

---

## What a good commit message does

A good commit message is:

- clear
- specific
- small in scope
- easy to scan in history
- easy to trust
- easy to revert

It answers one question:

**What changed in this commit?**

---

## Format

Use:

```text
<Verb> <thing> <qualifier>
```

Examples:

- `Add parser for ISO dates`
- `Fix rounding in interest calculation`
- `Rename transfer command fields`
- `Remove unused test fixture`
- `Document REPL workflow`

Preferred verbs:

- Add
- Fix
- Rename
- Refactor
- Remove
- Simplify
- Document
- Update
- Introduce

---

## Scope rule

One commit = one change.

If the commit message needs `and`, the commit is probably too big.

Bad:

```text
Add parser and refactor validation
```

Better:

```text
Add parser for string money amounts
Refactor amount validation helper
```

---

## Good examples

### Feature

```text
Add parser for string money amounts
```

### Bug fix

```text
Fix transfer rejection for insufficient funds
```

### Refactor

```text
Refactor balance update into pure helper
```

### Test

```text
Add tests for account closure rules
```

### Documentation

```text
Document standard REPL workflow
```

### Naming

```text
Rename account state predicate
```

---

## Bad examples

Too vague:

```text
Update stuff
```

Too broad:

```text
Fix tests and refactor naming and improve REPL flow
```

Too unclear:

```text
Change fn in core
```

Too emotional:

```text
Finally got this working
```

Too large:

```text
Implement transfers, reporting, and cleanup
```

---

## Commit body

Most commits should be a **single line only**.

Add a body only when the reason needs to be recorded.

Example:

```text
Fix rounding in interest calculation

Round only at the final step to avoid cumulative drift.
```

Use a body when:

- the reason is not obvious
- there is an important constraint
- the fix protects against a subtle bug
- future you will need the context

Keep it short.

---

## Commit only when ready

A commit is ready when:

- the change does one thing
- the behaviour was explored in the REPL
- the code is clear enough
- tests cover the behaviour
- `bb app:precommit` passes
- the diff has been reviewed

Typical sequence:

```bash
bb app:precommit
bb git:diff
bb git:status
git add .
git commit -m "Add money parser for string amounts"
```

---

## Quality test

Before committing, ask:

- Does this commit do one thing?
- Is the message specific?
- Would this make sense in three months?
- Could I revert this commit safely on its own?

If not, improve the scope or the message.

---

## Tone

Commit messages should be:

- calm
- factual
- direct
- boring in a good way

Do not be clever.
Do not be vague.
Do not narrate feelings.

The history should read like a sequence of clear decisions.

---

## Template

Use:

```text
<Verb> <thing> <qualifier if needed>
```

Examples:

- `Add parser for account numbers`
- `Fix nil handling in transfer command`
- `Refactor event folding helper`
- `Document git banking workflow`

---

## Final rule

A commit message should say, plainly, what is now safe to keep.

