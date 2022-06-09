(ns codewars.did-i-finish-my-sudoku)

(defn- correct-numbers? [values]
  (= (set values) #{1 2 3 4 5 6 7 8 9}))

(defn- transpose [m]
  (apply mapv vector m))

(def ^:private target-area
  [[1 1 1 2 2 2 3 3 3]
   [1 1 1 2 2 2 3 3 3]
   [1 1 1 2 2 2 3 3 3]
   [4 4 4 5 5 5 6 6 6]
   [4 4 4 5 5 5 6 6 6]
   [4 4 4 5 5 5 6 6 6]
   [7 7 7 8 8 8 9 9 9]
   [7 7 7 8 8 8 9 9 9]
   [7 7 7 8 8 8 9 9 9]])

(defn done-or-not [board]
  (let [columns-check (every? correct-numbers? board)
        rows-check (every? correct-numbers? (transpose board))
        area-check (->> (map vector board target-area)
                        (mapcat (fn [[column a-column]] (map vector column a-column)))
                        (group-by second)
                        vals
                        (map (fn [area] (map (fn [[a _index]] a) area)))
                        (every? correct-numbers?))]
    (if (and columns-check rows-check area-check)
      "Finished!"
      "Try again!")))

(def board-1
  [[5 3 4 6 7 8 9 1 2]
   [6 7 2 1 9 5 3 4 8]
   [1 9 8 3 4 2 5 6 7]
   [8 5 9 7 6 1 4 2 3]
   [4 2 6 8 5 3 7 9 1]
   [7 1 3 9 2 4 8 5 6]
   [9 6 1 5 3 7 2 8 4]
   [2 8 7 4 1 9 6 3 5]
   [3 4 5 2 8 6 1 7 9]])

(def board-2
  [[5 3 4 6 7 8 9 1 2]
   [6 7 2 1 9 0 3 4 9]
   [1 0 0 3 4 2 5 6 0]
   [8 5 9 7 6 1 0 2 0]
   [4 2 6 8 5 3 7 9 1]
   [7 1 3 9 2 4 8 5 6]
   [9 0 1 5 3 7 2 1 4]
   [2 8 7 4 1 9 6 3 5]
   [3 0 0 4 8 1 1 7 9]])

(done-or-not board-1)
(done-or-not board-2)

