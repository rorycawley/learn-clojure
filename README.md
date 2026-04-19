# learn-clojure

[![Clojure](https://img.shields.io/badge/Clojure-1.12.4-blue.svg)](https://clojure.org/)
[![Babashka](https://img.shields.io/badge/Babashka-Task%20Runner-orange.svg)](https://babashka.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](#license)

A small, REPL-first Clojure project designed for learning the language in tiny, manageable steps.

## Overview

This project is intentionally minimal.

It is built to support a simple learning loop:

- write a small piece of code
- evaluate it in the REPL
- change it
- reload it
- observe what happened

The aim is to build confidence with core Clojure concepts before adding frameworks, databases, or deployment complexity.

## Tech stack

- **Clojure** via `deps.edn`
- **Babashka** via `bb.edn`
- **clojure.test** for basic tests
- **REPL-first workflow**

## Project structure

```text
my-clojure-project/
├─ deps.edn
├─ bb.edn
├─ src/
│  └─ app/
│     └─ core.clj
├─ dev/
│  └─ user.clj
└─ test/
   └─ app/
      └─ core_test.clj
````

## Prerequisites

Install the following:

* Java
* Clojure CLI
* Babashka

## Getting started

Clone the repo and move into the project directory.

### Start the REPL

```bash
bb repl
```

Then in the REPL:

```clojure
(require 'user :reload)
(app/add 2 3)
(app/hello "Rory")
```

### Run the app

```bash
bb run
```

### Run the tests

```bash
bb test
```

## Development workflow

This project is designed around a tight feedback loop.

1. Edit code in `src/app/core.clj`
2. Save the file
3. Reload from the REPL
4. Call the function again
5. Observe the result

Example:

```clojure
(require 'user :reload)
(app/hello "Rory")
```

This keeps development small, fast, and interactive.

## Key files

### `src/app/core.clj`

Contains the main application code.

This is where you will usually put:

* simple functions
* experiments with core language features
* a small `-main` entry point

### `dev/user.clj`

Your REPL entry point and playground.

Use this namespace to:

* require your app code
* call functions manually
* inspect library docs
* experiment safely

### `test/app/core_test.clj`

Contains unit tests using `clojure.test`.

Start small and test behaviour, not complexity.

## Example commands

### Terminal

```bash
bb repl
bb run
bb test
```

### REPL

```clojure
(require 'user :reload)
(app/add 10 20)
(app/hello "Rory")
(doc map)
(find-doc "reduce")
(apropos "string")
```

## Learning approach

This repository is intended to support learning Clojure one step at a time.

Suggested order:

1. expressions
2. values with `def`
3. local bindings with `let`
4. functions with `defn`
5. calling functions in the REPL
6. editing and reloading code
7. writing simple tests
8. exploring collections
9. transforming data
10. building small programs

## Design principles

This project deliberately avoids early complexity.

It does **not** include:

* web frameworks
* database integration
* packaging/build pipelines
* production infrastructure
* large third-party libraries

That is by design.

The goal is to make the language easy to approach and easy to practice.

## Future ideas

Once the basics feel comfortable, possible next steps are:

* add richer collection exercises
* create a small command-line program
* introduce spec or Malli later
* add an nREPL-based editor workflow
* build a tiny data transformation project

## License

None
