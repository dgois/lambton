//: Playground - noun: a place where people can play

import UIKit

var str = "Hello, playground"

let three = 3
let matissa = 0.1456
//var sum = 3 + 0.1456
var sum = Double(three) + matissa
print(sum)

//let firstName: String = 'Denis'
//let lastName: String = 'Gois'
//
//var helloMessage = 'Hello \(firstName) \(lastName)'
//print(helloMessage)


let fullName = ("Denis", "Gois")
var helloMessage = "Hello \(fullName.0) \(fullName.1)"
print(helloMessage)

let (firstJohn, lastJohn) = ("John", "Due")
helloMessage = "Hello \(firstJohn) \(lastJohn)"
print(helloMessage)

let fullNameGislaine = (first: "Gislaine", last: "Gois")
helloMessage = "Hello \(fullNameGislaine.first) \(fullNameGislaine.last)"
print(helloMessage)

func square(number: Int?) -> Int? {
    guard let number = number, isValidNumber(n: number) else {
        print("Invalid number passed to square")
        return nil
    }
    return number * number
}

func isValidNumber(n: Int) -> Bool {
    return n > 0
}

print(square(number: 2))

