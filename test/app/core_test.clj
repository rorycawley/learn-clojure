(ns app.core-test
  (:require [clojure.test :refer [deftest is]]
            [app.core :as app]))

(deftest add-test
  (is (= 5 (app/add 2 3))))

(deftest hello-test
  (is (= "Hello, Rory!" (app/hello "Rory"))))
