//: Playground - noun: a place where people can play

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
