//: Playground - noun: a place where people can play

//import UIKit
//
//var str = "Hello, playground"
//
//let frame = CGRect(x: 0, y: 0, width: 150, height: 150)
//let customView = UISwitch(frame: frame)
//customView.onTintColor = UIColor.blueColor()
//customView.tintColor = UIColor.orangeColor()
//customView.isOn = true

//var a = 5, b = 2

//var c = 5 + b / ((a -= 1) + (a += 1))

var x = (10, 20, 30)

print(x.0)
print(x.1)
print(x.2)

var y = (x.0, x.1)
print(y.0)

x.0 = 100

print(y.0)
print(x.0)

var (a1, a2) = y
print(a1)
print(2)

var (b1, _, b2) = x
print(b1)
print(b2)

var j = 2
for i in 0...10 {
    j += j * i
}

var a = [10, 20, 30, 40, 50]
print([0])

var b = [Int]()
b.append(1)
b.append(1000)
print(b[0])
print("Size : \(b.count)")

b[0] = 2000

//Declare to store any data types values
var c = [Any]()
c.append(100)
c.append("Patel")

print(c[0])
print(c[1])
print("Size : \(c.count)")

var t = a[1...3]
print("Indice One from t \(t[1])")
t.append(50)
t[1]
print("Indice One from t \(t[1])")
for e in t {
    print(e)
}

a[1] = 21
print("Indice Fouth from t \(t[4])")

var treeDoubles = Array(repeating: 0.0, count: 3)
var anotherThreeDoubles = Array(repeating: 2.5, count: 3)

var sixDoubles = treeDoubles + anotherThreeDoubles

var shoppingList = ["Eggs", "Milk"]
shoppingList += ["Baking Powder"]
shoppingList += ["Chocolate Spread", "Cheese", "Butter", "Bread", "Cookies"]
shoppingList[4...5] = ["Bananas", "Apples"]
shoppingList.insert("Maple Syrup", at: 0)
for (index, value) in shoppingList.enumerated() {
    print("Item \(index): \(value)")
}

var m = [Int]()
m.append(1)
m.capacity

shoppingList.sorted()
shoppingList.reverse()
shoppingList.count
shoppingList.capacity

//func sorterForItemNameDESC(first, second) -> Bool {
//    return first > second
//}
//shoppingList.sort(by: sorterForItemNameDESC)

var letters = Set<Character>()
letters.insert("a")
letters = []

var favoriteGenres: Set<String> = ["Rock", "Classical", "Hip hop", "Rock"]
favoriteGenres.insert("Jazz")
favoriteGenres.sorted()
favoriteGenres.contains("Funk")
print(favoriteGenres)

favoriteGenres[favoriteGenres.index(favoriteGenres.startIndex, offsetBy: 2)]

print("Sorted")
for genre in favoriteGenres.sorted() {
    print(genre)
}

let oddDigits: Set = [1, 3, 5, 7, 9]
let evenDigits: Set = [0, 2, 4, 6, 8]
let singleDigitPrimeNumbers: Set = [2, 3, 5, 7]

oddDigits.union(evenDigits).sorted()
oddDigits.intersection(evenDigits).sorted()
oddDigits.subtracting(singleDigitPrimeNumbers).sorted()
oddDigits.symmetricDifference(singleDigitPrimeNumbers).sorted()

let houseAnimals: Set = ["🐶", "🐱"]
let farmAnimals: Set = ["🐮", "🐔", "🐑", "🐶", "🐱"]
let cityAnimals: Set = ["🐦", "🐭"]

houseAnimals.isSubset(of: farmAnimals)
farmAnimals.isSuperset(of: houseAnimals)
farmAnimals.isDisjoint(with: cityAnimals)

//Dictionaries
var countryLanguages : Dictionary<String, String> = ["India":"Hindi", "Canada":"English", "Brazil":"Portugese"]

countryLanguages.updateValue("Brazilian Portuguese", forKey: "Brazil")

print(countryLanguages)
print(countryLanguages.description)
print(countryLanguages["Brazil"])

for (k,v) in countryLanguages {
    print("\(k) -> \(v)")
}

print("Keys")
for country in countryLanguages.keys {
    print("Coutry: \(country)")
}

var initalizationTest = [String: AnyObject]()
initalizationTest["firstName"] = "Denis" as AnyObject
initalizationTest["age"] = 29 as AnyObject

print(initalizationTest)
var student = [String: String]()
student[]
