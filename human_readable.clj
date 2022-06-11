(ns human-readable
  (:require [clojure.string :as string]))
;; https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/clojure

(defn format-seconds [secs]
  (cond
    (zero? secs) nil
    (= secs 1) "1 second"
    (< secs 60) (str secs " seconds")
    :else secs))

(defn format-munites [mins]
  (cond
    (zero? mins) nil
    (= mins 1) "1 minute"
    :else (str mins " minutes")))

(defn format-munites-seconds [secs]
  (let [m (quot secs 60)
        s (mod secs 60)]
    (->> [(format-munites m) (format-seconds s)]
         (remove nil?)
         (string/join ", "))))

(defn format-hours [hours]
  (cond
    (zero? hours) nil
    (= hours 1) "1 hour"
    :else (str hours " hours")))

(defn format-hms [secs]
  (let [h (quot secs 3600)
        ms (mod secs 3600)
        formatted-h (format-hours h)
        formatted-ms (when-not (zero? ms)
                       (format-munites-seconds ms))]
    (->> [formatted-h formatted-ms]
         (remove nil?)
         (string/join ", "))))

(defn format-days [days]
  (cond
    (zero? days) nil
    (= days 1) "1 day"
    :else (str days " days")))

(defn format-dhms [secs]
  (let [d (quot secs 86400)
        hms (mod secs 86400)
        formatted-d (format-days d)
        formatted-hms (when-not (zero? hms) (format-hms hms))]
    (->> [formatted-d formatted-hms]
         (remove nil?)
         (string/join ", "))))

(defn format-years [years]
  (cond
    (zero? years) nil
    (= years 1) "1 year"
    :else (str years " years")))

(defn format-ydhms [secs]
  (let [y (quot secs 31536000)
        dhms (mod secs 31536000)
        formatted-y (format-years y)
        formatted-dhms (when-not (zero? dhms) (format-dhms dhms))]
    (->> [formatted-y formatted-dhms]
         (remove nil?)
         (string/join ", "))))

(defn formatDuration [secs]
  (let [words
        (-> (cond
              (zero? secs) "now"
              (< secs 59) (format-seconds secs)
              (< secs 3600) (format-munites-seconds secs)
              (< secs (* 3600 24)) (format-hms secs)
              (< secs (* 3600 24 365)) (format-dhms secs)
              :else
              (format-ydhms secs))
            (string/split #", "))
        last-words (->> words
                        (take-last 2)
                        (string/join " and "))]
    (if (< 2 (count words))
      (string/join ", " (conj (vec (drop-last 2 words)) last-words))
      last-words)))

(ns human-readable-test
  (:require [clojure.test :refer :all]
            [human-readable :refer [formatDuration]]))

(deftest test-human-readable-duration
  (is (= (formatDuration 0) "now"))
  (is (= (formatDuration 1) "1 second"))
  (is (= (formatDuration 62) "1 minute and 2 seconds"))
  (is (= (formatDuration 120) "2 minutes"))
  (is (= (formatDuration 3600) "1 hour"))
  (is (= (formatDuration 3662) "1 hour, 1 minute and 2 seconds"))
  (is (= (formatDuration 15731080) "182 days, 1 hour, 44 minutes and 40 seconds"))
  (is (= (formatDuration 132030240) "4 years, 68 days, 3 hours and 4 minutes")))

(run-tests)
