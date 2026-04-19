(ns app.core)

(defn add [a b]
  (+ a b))

(defn hello [name]
  (str "Hello, " name "!"))

(defn -main [& _args]
  (println (hello "Rory")))
