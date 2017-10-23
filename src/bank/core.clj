(ns bank.core
  (:require [clojure.string :as str]))

(defn init-account []
  [])

(defn- calculate-balance [bank-account]
  (if (empty? bank-account)
    0
    (:balance (first bank-account))))

(defn deposit [bank-account amount date]
  (let [previous-balance (calculate-balance bank-account)]
    (conj bank-account {:date date :amount amount :balance (+ previous-balance amount)})))

(defn withdraw [bank-account amount date]
  (deposit bank-account (- amount) date))

(defn- print-statement-line [statement-line]
  (str (:date statement-line) " | " (:amount statement-line) " | " (:balance statement-line)))

(defn print-statement [bank-account]
  (conj (reverse (map print-statement-line bank-account)) "DATE | AMOUNT | BALANCE"))