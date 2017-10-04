import Foundation

class Employee {
    var name: String?
    var age: Int?
    
    func calcBirthYear() -> Int {
        return 2017 - age!
    }
}
