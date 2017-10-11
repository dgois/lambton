

public class PartTime: Employee {
    
    private var _hourlyRate : Int
    public var hourlyRate : Int {
        get {
            return _hourlyRate
        }
        
        set {
            _hourlyRate = newValue
        }
    }
    
    private var _numberHoursWorked : Int
    public var numberHoursWorked : Int {
        get {
            return _numberHoursWorked
        }
        
        set {
            _numberHoursWorked = newValue
        }
    }
    
    override init() {
        _hourlyRate = 0
        _numberHoursWorked = 0
        super.init();
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int) {
        _hourlyRate = pHourlyRate
        _numberHoursWorked = pNumberHoursWorked
        super.init(ppName, ppAge)
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int, ppV: Vehicle) {
        _hourlyRate = pHourlyRate
        _numberHoursWorked = pNumberHoursWorked
        super.init(ppName, ppAge, ppV)
    }
    
    override func getTypeOfEmployee() -> String {
        return "Part Time"
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Rate: \(hourlyRate)\n  -Hours Worked: \(numberHoursWorked)"
    }
}
