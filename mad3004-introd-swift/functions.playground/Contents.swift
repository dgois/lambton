//: Playground - noun: a place where people can play

import UIKit

func add() {
    print("I am in User Defiend Function")
}

add()

func welcome(name: String) {
    print("Welcome \(name)")
}

func welcome(_ a :String){
    print("Welcome \(a)")
}

welcome(name: "Denis")
welcome("Gislaine")

func sum(a :Int, b :Int) {
    let c = a + b
    print("Sum : \(c)")
}

sum(a: 10, b: 15)

func mul(a :Int, b :Int) -> Int {
    let c = a * b
    return c
}

print(mul(a: 2, b: 6))

func swipe(a :Int, b :Int) -> (Int, Int) {
//    var temp = a
//    a = b
//    b = temp
    return (b, a)
}

var x :Int, y :Int
(x, y) = swipe(a: 1, b: 2)
print(x, y)
(_, y) = swipe(a: 14, b: 7)
print(y)

func swapTwoDoubles(aa: inout Double, bb: inout Double) {
    let temp = aa
    aa = bb
    bb = temp
}

var c = 2.2, d = 3.3
swapTwoDoubles(aa: &c, bb: &d)
print(c, d)

func display(a :[Int]) {
    for n in a {
        print(n)
    }
}

display(a: [1, 2, 3, 4, 5])

func simpleInterest(amount :Double, noOfYears :Double, rate :Double = 5.0) -> Double {
    let si = ((amount * rate) * noOfYears) / 100
    return si
}

let result = simpleInterest(amount: 10, noOfYears: 1)
print("Result is : \(result)")

func anotherWayToUseDefautVar(varOne :String, _ varTwo :String = "", varThree :String) {
    print("Print required parameters: \(varOne)\(varTwo) \(varThree)")
}

anotherWayToUseDefautVar(varOne: "Denis", varThree: "Gois")
anotherWayToUseDefautVar(varOne: "Denis", " Willian de Toledo de", varThree: "Gois")

func arithmeticMean(_ numbers: Double...) -> Double {
    var total: Double = 0
    for number in numbers {
        total += number
    }
    
    return total / Double(numbers.count)
}

print("Arithmetic Mean is \(arithmeticMean(1, 2, 3))")

func printMatrix(cols :Int, rows :Int, content: String) {
    for _ in 0...cols {
        for _ in 0...rows {
            print(content)
        }
        print("\n")
    }
}

func sumArrays(arrays :[Int]...) -> [Int] {
    
    let a :[Int] = arrays[0]
    let b :[Int] = arrays[1]
    var r = [Int]()
    
    for i in 0..<a.count {
        r.append(a[i] + b[i])
    }
    return r
}

func sumArraysSecondVersion(arrays :[Int]...) -> [Int] {
    
    var count = 0
    var r = [Int]()
    
    var repeted = Set<Int>()
    for i in 0..<arrays.count {
        repeted.insert(arrays[i].count)
    }
    
    guard repeted.count < 1 else {
        print("Arrays must be the same size")
        return [Int]()
    }
    
    while count < arrays[0].count {
        var sum = 0
        for i in 0..<arrays.count {
            sum += arrays[i][count]
        }
        r.append(sum)
        count += 1
    }
    
    return r
}

let sumArray = sumArrays(arrays: [1, 2, 3, 4, 5], [1, 2, 3, 4, 5])
print(sumArray)
let sumArraySec = sumArraysSecondVersion(arrays: [1, 2, 3, 4, 5], [1, 2, 3, 4, 5], [1, 2, 3, 4, 5])
print(sumArraySec)

var S = "1 12 3 9 5 8 2"
func parseArray(arrayAsString :String) -> [Int] {
    let array = arrayAsString.characters.split{$0 == " "}
    var intArray = [Int]()
    for c in array {
        if let number = Int(String(c)) {
            intArray.append(number)
        }
    }
    return intArray
}
print(parseArray(arrayAsString: S))

//functions type (Int, Int) -> Int
func addTwoInts(_ a: Int, _ b: Int) -> Int {
    return a + b
}

func multiplyTwoInts(_ a: Int, _ b: Int) -> Int {
    return a * b
}

//function type () -> Void
func printHelloWorld() {
    print("hello, world")
}

var mathFunction: (Int, Int) -> Int = addTwoInts

print(mathFunction(10, 10))

mathFunction = multiplyTwoInts

print(mathFunction(10, 10))

func printMathResult(_ mathFunction: (Int, Int) -> Int, _ a: Int, _ b: Int) {
    print("Result: \(mathFunction(a, b))")
}

printMathResult(addTwoInts, 13, 45)
printMathResult(multiplyTwoInts, 13, 45)

func stepForward(_ input: Int) -> Int {
    return input + 1
}

func stepBackward(_ input: Int) -> Int {
    return input - 1
}

func chooseStepFunction(backward: Bool) -> (Int) -> Int {
    return backward ? stepBackward : stepForward
}

var currentValue = 10

while currentValue != 0 {
    let moveNearerToZero = chooseStepFunction(backward: currentValue > 0)
    currentValue = moveNearerToZero(currentValue)
    print(currentValue)
}
