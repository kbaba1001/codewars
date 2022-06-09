(ns removed-numbers.core)

;; (sum n) = a * b + a + b  [1 < a <= n, 1 < b <= n, a != b]
;;           (+ (* a b) a b)
;; (java.util.Collections/binarySearch [0 1 2 3] 2 compare)
(defn remove-nb [n]
  (let [total (reduce + (range 1 (inc n)))
        arr (vec (reverse (range 1 (inc n))))]
    (->>
     (loop [rest-arr arr
            result []]
       (let [a (first rest-arr)]
         (if (or (< (+ (* a a) a a) total) (empty? rest-arr))
           result
           (let [bi (java.util.Collections/binarySearch arr total (fn [b t] (compare t (+ (* a b) a b))))]
             (recur
              (next rest-arr)
              (if (< 0 bi)
                (conj result [a (get arr bi)])
                result))))))
     (reduce (fn [r v]
               (if (= (first v) (second v))
                 (conj r v)
                 (conj r (reverse v) v)))
             [])
     (sort-by first))))

(remove-nb 26)

(ns removed-numbers.core-test
  (:require [clojure.test :refer :all]
            [removed-numbers.core :refer :all]))

(deftest a-test1
  (testing "Test 1"
    (is (= (remove-nb 26) [[15 21] [21 15]]))))
(deftest a-test2
  (testing "Test 2"
    (is (= (remove-nb 100) []))))

(run-tests)
