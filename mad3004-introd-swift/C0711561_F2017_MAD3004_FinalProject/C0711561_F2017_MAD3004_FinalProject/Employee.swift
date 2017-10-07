public class Employee: IPrintable {
    
    public var name: String
    public var age: Int
    public var v: Vehicle? // optional
    
    init() {
        name = ""
        age = 0
        v = nil
    }
    
    init (_ pName: String,_ pAge: Int) {
        name = pName
        age = pAge
        v = nil
    }
    
    init (_ pName: String,_ pAge: Int, _ pV: Vehicle) {
        name = pName
        age = pAge
        v = pV
    }
    
    func getTypeOfEmployee() -> String {
        fatalError("This method must be overridden")
    }
    
    func calcBirthYear() -> Int {
        return (2017 - self.age)
    }
    
    func calcEarnings() -> Double {
        return 1000.00
    }
    
    func printMyData() -> String {
        let personalInfo = "Name: \(name)\nAge:\(age)\nYear of Birth: \(calcBirthYear())\n"
        
        var vehicleInfo = "Employee does not have vehicle"
        if let vehicle = v {
            vehicleInfo = "Employee has a \(vehicle.getVehicleType())\n\(vehicle.printMyData())"
        }
        
        let employeeInfo = "Employee is \(getTypeOfEmployee())"
        
        return personalInfo + vehicleInfo + employeeInfo
    }
}
