//: Playground - noun: a place where people can play

// Methods and Properties
class Student {
    let x = 100
    var id :Int = 0
    var name :String = ""
    var total :Double {
        set {
            print("set of total")
            per = newValue / 5 * 100
        }
        
        get {
            print("get of total")
            
            return self.total
        }
    }
    var per:Double {
        set {
            print("set of per")
            if newValue >= 80.0{
                result = "First"
            } else if newValue >= 70.0{
                result = "Second"
            } else if newValue >= 60.0{
                result = "Third"
            } else if newValue >= 50.0{
                result = "Pass"
            } else {
                result = "Fail"
            }
        }
        
        get {
            print("get of per")
            return self.per
        }
    }
    var result:String = ""
    
    
    func setDat(id :Int, name :String, marks :Double) {
        self.id = id
        self.name = name
        total = marks
    }
    
    func displayData() {
        print("Student ID : \(id)")
        print("Student Name : \(name)")
        
        //print("Student Total Marks : \(total)")
        //print("Student Percentege : \(per)")
        print("Student Result : \(result)")
    }
}

var s1 = Student()

s1.setDat(id: 1, name: "Denis", marks: 71)
print("\n")
s1.displayData()


//print(type(of: s1))


// Constructor and Destructor
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
        print("Employee object destroyed. Employee id \(eid)")
    }
    
    func display() {
        print(self.eid!, self.ename!, self.salary!)
    }
    
}

var e1 = Employee()
print(e1.eid, e1.ename, e1.salary)
e1.display()

var e2 = Employee(employeeId: 1, employeeName: "Denis Gois", employeeSalary: 5000.0)
e2.display()

e1 = e2

// All types are structs and not classes
/*
var eid = Int()
var gender = Bool()
var enm = String()
var salary = Double()
print(eid, gender, enm, salary)
*/
