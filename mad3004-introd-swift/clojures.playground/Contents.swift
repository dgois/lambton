//: Playground - noun: a place where people can play

//import UIKit

var str = "Hello, playground with clojure"

let names = ["Chris", "Alex", "Ewa", "Barry", "Daniella"]
let sortedNames = names.sorted { (_ s1: String, _ s2: String) -> Bool in
    return s1 > s2
}

let sortedNamesReversed = names.sorted { (_ s1: String, _ s2: String) -> Bool in
    return s1 < s2
}

print(names)
print(sortedNames)
print(sortedNamesReversed)

let number = [3, 7, 1, 99, 43, 23, 11, 63, 10]

let sortedNumbers = number.sorted { n1, n2 in n1 < n2 }

print(sortedNumbers)
