//: Playground - noun: a place where people can play
import UIKit

class Employee {
    var eid :Int?
    var ename :String?
    var salary :Double?
    
    //Default Constructor
    init() {
        eid = 0
        ename = String()
        salary = 0.0
    }
    
    //Parametrize Constructor
    init(employeeId eid :Int, employeeName ename :String, employeeSalary salary :Double) {
        self.eid = eid
        self.ename = ename
        self.salary = salary
    }
    
    convenience init(emp :Employee) {
        self.init(
            employeeId: emp.eid!,
            employeeName: emp.ename!,
            employeeSalary: emp.salary!)
    }
    
    //Destructor
    deinit {
        print("Employee object destroyed")
    }
    
    func display() {
        print(self.eid!, self.ename!, self.salary!)
    }
}

class FullTimeEmployee : Employee {
    var noOfPayedLeaves: Int?
    
    override init() {
        super.init()
        noOfPayedLeaves = 0
    }
    
    init(employeeId eid :Int, employeeName ename :String, employeeSalary salary :Double, noOfPayedLeaves : Int) {
        super.init(employeeId: eid, employeeName: ename, employeeSalary: salary)
        self.noOfPayedLeaves = noOfPayedLeaves
    }
    
    final func display(message :String) {
        print("Welcome \(String(self.ename!))")
    }
    
    override func display() {
        super.display()
        print("No. of leaves : \(self.noOfPayedLeaves!)")
    }
}

class ParttimeEmployee : Employee {
    var startTime :Date?
    var endTime :Date? {
        didSet {
            self.noOfHours = (endTime?.timeIntervalSince(startTime!))! / 60 / 60
        }
    }
    var noOfHours :Double?
    
    init(employeeId eid: Int, employeeName ename: String, employeeSalary salary: Double, startTime :Date) {
        super.init(employeeId: eid, employeeName: ename, employeeSalary: salary)
        self.startTime = startTime
    }
    
    //Erro - method does not override any method from its superclass
//    override func display(message :String) {
//        print("Welcome \(String(self.ename!))")
//    }
    
    override func display() {
        super.display()
        print("Number of Hours: \(noOfHours!)")
    }
}

var f1 = FullTimeEmployee(employeeId: 1, employeeName: "Denis", employeeSalary: 1000, noOfPayedLeaves: 12)
var f2 = FullTimeEmployee(employeeId: 2, employeeName: "Gislaine", employeeSalary: 1000, noOfPayedLeaves: 12)

f1.display()

var p1 = ParttimeEmployee(employeeId: 1, employeeName: "Arthur", employeeSalary: 5000, startTime: Date())
p1.endTime = Date() + (60 * 60 * 4)

p1.display()

class Vehicle {
    static var noOfObject = 0
    let specificMaxSpeed = 100
    
    init() {
        Vehicle.noOfObject += 1
    }
    
    deinit {
        print("Object destroyed")
    }
    
    static func getNoOfObject() -> Int {
        return noOfObject
    }
    
    func getSpecificMaxSpeed() -> Int {
        return specificMaxSpeed
    }
    
    func noise() {
        print("Default sound")
    }
}

class Car : Vehicle {
    override func noise() {
        print("Car sound")
    }
}

var c1 = Car()
c1.getSpecificMaxSpeed()

var v1 = Vehicle()
v1.getSpecificMaxSpeed()

print(Vehicle.getNoOfObject())
//v1.MAX_SPEED error: static member 'MAX_SPEED' cannot be used on instance of type 'Vehicle'
//v1.specificMaxSpeed = 190 error: cannot assign to property: 'specificMaxSpeed' is a 'let' constant

var v2 :Vehicle
v2 = Car()
v2.noise()


class Student {
    var course = Course()
}

class Course {
    var subject : Subject?
}

class Subject {
    var marks = 100
}

let student1 = Student()

student1.course.subject = Subject()

student1.course.subject?.marks




let number: Int? = Optional.some(42)
let noNumber: Int? = Optional.none
print(noNumber == nil)
// Prints "true"

let shortForm: Int? = Int("42")
let longForm: Optional<Int> = Int("42")



