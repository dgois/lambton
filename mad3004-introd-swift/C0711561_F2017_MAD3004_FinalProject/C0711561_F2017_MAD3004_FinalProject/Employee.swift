public class Employee: IPrintable {
    
    private var _name: String
    public var name: String {
        get {
            return _name
        }
        
        set {
            _name = newValue
        }
    }
    
    private var _age: Int
    public var age: Int {
        get {
            return _age
        }
        
        set {
            _age = newValue
        }
    }
    
    private var _v: Vehicle? // optional
    public var v: Vehicle? {
        get {
            return _v
        }
        
        set {
            _v = newValue
        }
    }
    
    init() {
        _name = ""
        _age = 0
        _v = nil
    }
    
    init (_ pName: String,_ pAge: Int) {
        _name = pName
        _age = pAge
        _v = nil
    }
    
    init (_ pName: String,_ pAge: Int, _ pV: Vehicle) {
        _name = pName
        _age = pAge
        _v = pV
    }
    
    func calcBirthYear() -> Int {
        return (2017 - self.age)
    }
    
    func getTypeOfEmployee() -> String {
        fatalError("This method must be overridden")
    }
    
    func calcEarnings() -> Double {
        fatalError("This method must be overridden")
    }
    
    func printMyData() -> String {
        let line = "\n-------------------------------------------"
        let personalInfo = "\nName: \(name)\nAge: \(age)\nYear of Birth: \(calcBirthYear())\n"
        
        var vehicleInfo = "\nEmployee has no vehicle registered"
        if let vehicle = v {
            vehicleInfo = "\nEmployee has a \(vehicle.getVehicleType())\n\(vehicle.printMyData())"
        }
        
        let employeeInfo = "\nEmployee is \(getTypeOfEmployee())"
        
        return line + personalInfo + vehicleInfo + employeeInfo
    }
}
