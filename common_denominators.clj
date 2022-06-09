(ns com-denom.core)

(defn gcd [a b]
  (if (= b 0)
    a
    (recur b (mod a b))))

(defn lcm [a b]
  (let [d (gcd a b)]
    (* (/ a d) b)))

(defn convert-fracts [lst]
  (let [values (map second lst)
        denominator (reduce lcm values)]
    (map (fn [[n d]] [(/ (* denominator n) d) denominator]) lst)))

(ns com-denom.core-test
  (:require [clojure.test :refer :all]
            [com-denom.core :refer :all]))

(deftest a-test1
  (testing "Test 1"
    (def a  [[1, 2], [1, 3], [1, 4]])
    (def b  [[6, 12], [4, 12], [3, 12]])
    (is (= (convert-fracts a) b))))

(run-tests)
