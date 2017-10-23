(ns bank.core-test
  (:require [clojure.test :refer :all]
            [bank.core :refer :all]))

(deftest statement-print-test
  (testing "Empty account prints only header"
    (let [bank-account (init-account)]
      (is (=
            ["DATE | AMOUNT | BALANCE"]
            (print-statement bank-account)))))
  (testing "Account with deposit prints a statement line"
    (let [bank-account (deposit (init-account) 99.99 "23/10/2017")]
      (is (=
            ["DATE | AMOUNT | BALANCE"
             "23/10/2017 | 99.99 | 99.99"]
            (print-statement bank-account)))))
  (testing "Account with withdraw prints a statement line"
    (let [bank-account (withdraw (init-account) 99.99 "23/10/2017")]
      (is (=
            ["DATE | AMOUNT | BALANCE"
             "23/10/2017 | -99.99 | -99.99"]
            (print-statement bank-account)))))
  (testing "Account with deposit and withdraw prints two statement lines with the actions"
    (let [bank-account (-> (init-account)
                           (deposit 100 "23/10/2017")
                           (withdraw 25 "24/10/2017"))]
      (is (=
            ["DATE | AMOUNT | BALANCE"
             "24/10/2017 | -25 | 75"
             "23/10/2017 | 100 | 100"]
            (print-statement bank-account)))))
  )
