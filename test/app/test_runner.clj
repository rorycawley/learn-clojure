(ns app.test-runner
  (:require [clojure.test :as t]
            [app.core-test]))

(defn -main [& _args]
  (let [{:keys [fail error]} (t/run-tests 'app.core-test)]
    (System/exit
     (if (zero? (+ fail error))
       0
       1))))
