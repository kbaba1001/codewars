;; https://www.codewars.com/kata/554f76dca89983cc400000bb/train/clojure
(ns dioph-equa.core)

(defn sol-equa [n]
  ; your code
  )



(ns dioph-equa.core-test
  (:require [clojure.test :refer :all]
            [dioph-equa.core :refer :all]))

(deftest a-test1
  (testing "Test 1"
    (is (= (sol-equa 5) [[3, 1]]))))
(deftest a-test2
  (testing "Test 2"
    (is (= (sol-equa 12) [[4, 1]]))))
(deftest a-test3
  (testing "Test 3"
    (is (= (sol-equa 13) [[7, 3]]))))
(deftest a-test4
  (testing "Test 4"
    (is (= (sol-equa 16) [[4, 0]]))))

