(ns max-sequence)
;; https://www.codewars.com/kata/54521e9ec8e60bc4de000d6c/train/clojure

(defn max-sequence [xs]
  (apply
   max
   (loop [arr xs
          result []]
     (if (empty? arr)
       (conj result 0)
       (let [subarr-max (->> (inc (count arr))
                             (range 1)
                             (map (fn [a] (reduce + (take a arr))))
                             (apply max))]
         (recur (next arr) (conj result subarr-max)))))))

;; -------------

(ns max-sequence-test
  (:require [clojure.test :refer :all]
            [max-sequence :refer :all]))

(deftest simple
  (is (= (max-sequence  [-2, 1, -3, 4, -1, 2, 1, -5, 4]) 6)))

(run-tests)
