//: Playground - noun: a place where people can play

import UIKit

protocol IDisplay {
    init()
    func display()
}

protocol IGreetings {
    
    func greet() -> String
}

class Fruit : IDisplay, IGreetings {
    
    var name: String?
    
    required init() {
        
    }
    
    init(name: String) {
        self.name = name
    }
    
    func display() {
        print("I've created a fruit")
    }
    
    func greet() -> String {
        return "Welcome to Protocol Programming"
    }
    
    func greet(_ name: String) {
        print("I will fire the error", name)
    }
}

var d = Fruit()
d.display()
print(d.greet())
d.greet("Denis")

var e:IDisplay = Fruit()
e = d as Fruit
e.display()

class Apple : Fruit {
    required init() {
        super.init(name: "Apple")
    }
    
    override init(name: String) {
        super.init()
        self.name = name
    }
}

print("\n--- Aple starts from here ------")
var apple1 = Apple()
apple1.display()

//Exercise
print("\n --- Exercise -----")

protocol ICalculate {
    static var MAX: Int{get set}
    var total: Int{get}
    func calc(_ a: Int, _ b: Int) -> Int
}

class Addition : ICalculate {
    static var MAX: Int = 100
    
    var total: Int = 0
    
    func calc(_ a: Int, _ b: Int) -> Int {
        total = a + b
        return total
    }
}

class Substract : ICalculate {
    static var MAX: Int = 100
    
    var total: Int = 0

    func calc(_ a: Int, _ b: Int) -> Int {
        total = a - b
        return total
    }
}

class Division : ICalculate {
    static var MAX: Int = 100
    
    var total: Int = 0
   
    func calc(_ a: Int, _ b: Int) -> Int {
        total = a / b
        return total
    }
}

class Multiplication : ICalculate {
    static var MAX: Int = 100
    
    var total: Int = 0
    
    func calc(_ a: Int, _ b: Int) -> Int {
        total = a * b
        return total
    }
}

var op1: ICalculate = Addition()
print(op1.calc(1, 2))
op1 = Substract()
print(op1.calc(1, 2))
op1 = Division()
print(op1.calc(1, 2))
op1 = Multiplication()
print(op1.calc(1, 2))

// Extentions
print("\n --- Extentions -----")
extension String {
    var f1: String { return "Welcome " + self }
    var f2: String {
        let formartter = NumberFormatter()
        formartter.numberStyle = .decimal
        let number = Double(self)
        let result: NSNumber = (number! / 100) as NSNumber
        return formartter.string(from: result)!
    }
    var f3: String {
        let formartter = NumberFormatter()
        formartter.numberStyle = .currency
        let number = Double(self)
        let result: NSNumber = (number!) as NSNumber
        return formartter.string(from: result)!
    }
}

print("Denis".f1)
print("12345".f2)
print("50".f3)

extension String
{
    var length: Int {
        get {
            return self.count
        }
    }
    
    func contains(s: String) -> Bool
    {
        return self.contains(s)
    }
    
    func getVowels() -> String {
        return vowels.joined()
    }
    
    func getConsonants() -> String {
        return consonants.joined()
    }
    
    private func containsInArray(_ array: [String]) -> [String] {
        var newString = [String]()
        for char in self.characters {
            
            if array.contains(char.description) {
                newString.append(char.description)
            }
        }
        return newString
    }
    
    var vowels: [String]
    {
        get
        {
            let v = ["a", "e", "i", "o", "u"]
            return self.containsInArray(v)
        }
    }
    
    private var consonants: [String]
    {
        get
        {
            let c = ["b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"]
            return self.containsInArray(c)
        }
    }
}

"denis".getVowels()
"denis".getConsonants()
