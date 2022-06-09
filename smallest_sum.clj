(ns smallest-sum.core)
;; https://www.codewars.com/kata/52f677797c461daaf7000740/train/clojure

(defn slow-solution
  [arr]
  (prn arr)
  (if (every? (partial = (first arr)) arr)
    (reduce + arr)
    (let [sorted-arr (sort arr)]
      (recur (reduce (fn [r v]
                       (conj r (if (and (seq r) (< (last r) v))
                                 (- v (last r))
                                 v)))
                     []
                     sorted-arr)))))

;; (Thread/sleep 1000)
;; (prn arr)

(defn solution
  [arr]
  (loop [min-number (apply min arr)
         diff arr]
    (if (every? (fn [n] (zero? (mod n min-number))) arr)
      (* min-number (count arr))
      (let [new-diff (->> (next diff)
                          (reduce (fn [r n]
                                    (conj r (java.lang.Math/abs (- (last r) n))))
                                  [(first arr)])
                          (remove zero?)
                          (#(conj % min-number)))
            new-min-number (apply min new-diff)]
        (Thread/sleep 1000)
        (prn new-diff)
        (prn new-min-number)
        (recur new-min-number new-diff)))))

(solution [30 12])

(ns smallest-sum.test
  (:require [smallest-sum.core :refer :all]
            [clojure.test :refer :all]))

(deftest sample-test-cases
  (is (= 3 (solution [1,21,55])))
  (is (= 5 (solution [3,13,23,7,83])))
  (is (= 12 (solution [4,16,24])))
  (is (= 12 (slow-solution [30,12])))
  (is (= 132 (solution [60,12,96,48,60,24,72,36,72,72,48])))
  (is (= 923 (solution [71,71,71,71,71,71,71,71,71,71,71,71,71])))
  (is (= 22 (solution [11,22])))
  (is (= 9 (solution [9])))
  (is (= 1 (solution [1])))
  (is (= 18 (solution [9,9]))))

(run-tests)
