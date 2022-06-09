(ns divpar7.core)

(defn seven [m]
  (let [seven-first (next (take 10 (iterate (partial + 7) 0)))]
    (loop [m* m
           n 1]
      (let [x (int (/ m* 10))
            y (mod m* 10)
            a (- x (* 2 y))]
        (if (= a seven-first)
          [a n]
          (recur a (inc n)))))))
