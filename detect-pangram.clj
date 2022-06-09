(ns Pangram
  (:require [clojure.string :as string]
            [clojure.set :as set]))

(defn pangram?
  [s]
  (let [alphabet (set (map (comp str char) (range 97 123)))
        target (-> s
                   string/lower-case
                   (string/replace #" " "")
                   (string/split #"")
                   set)]
    (empty? (set/difference alphabet target))))


(ns PangramTest
  (:require [clojure.test :refer :all]
            [Pangram :refer [pangram?]]))

(deftest Tests
  (is (= (pangram? "The quick brown fox jumps over the lazy dog.") true))
  (is (= (pangram? "Pack my box with five dozen liquor jugs.") true))
  (is (= (pangram? "Not every sentence is a a pangram. An example") false)))

(run-tests)
