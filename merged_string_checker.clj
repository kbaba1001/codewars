(ns stringmerge.core
  (:require [clojure.string :as str]))
;; https://www.codewars.com/kata/54c9fcad28ec4c6e680011aa/train/clojure

(defn same-chars [string1 string2]
  (= (sort string1) (sort string2)))

#_(defn correct-order [string p]
    (->> (str/split p #"")
         (str/join ".*")
         (#(str ".*" % ".*"))
         re-pattern
         (#(re-find % string))
         boolean))

(defn correct-order [string p]
  (let [s (first string)
        fp (first p)]
    (cond
      (nil? fp) true
      (nil? s) false
      (= s fp) (recur (next string) (next p))
      :else (recur (next string) p))))

(defn is-merge [string p1 p2]
  (and (same-chars string (str p1 p2))
       (correct-order string p1)
       (correct-order string p2)))

(is-merge "codewars" "code" "wasr")
(is-merge "Bananas from Bahamas" "Bahas" "Bananas from am")

;; -------------------

(ns stringmerge.test
  (:require [clojure.test :refer :all]
            [stringmerge.core :refer [is-merge]]))


(deftest example-tests
  (testing "codewars is code and wars"
    (is (is-merge "codewars" "code" "wars")))
  (testing "codewars is cdw and oears"
    (is (is-merge "codewars" "cdw" "oears")))
  (testing "codewars are not codwars"
    (is (not (is-merge "codewars" "cod" "war"))))
  (testing "codewars are not codeswars"
    (is (not (is-merge "codewars" "codes" "wars"))))
  (testing "Bananas from Bahamas"
    (is (is-merge "Bananas from Bahamas" "Bahas" "Bananas from am"))))

(run-tests)
