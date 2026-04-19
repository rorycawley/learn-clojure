(ns user
  (:require [app.core :as app]
            [clojure.repl :refer [doc apropos find-doc]]))

(comment
  (app/add 2 3)
  (app/hello "Rory")
  (doc map)
  (find-doc "map")
  (apropos "str"))
