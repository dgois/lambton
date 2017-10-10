public class FullTime : Employee {
    let typeOfEmployee = "FullTime"
    
    private var _salary: Int
    public var salary: Int {
        get {
            return _salary
        }
        
        set {
            _salary = newValue
        }
    }
    
    private var _bonus: Int
    public var bonus: Int {
        get {
            return _bonus
        }
        
        set {
            _bonus = newValue
        }
    }
    
    override init() {
        _salary = 0
        _bonus = 0
        super.init();
    }
    
    init(ppName: String, ppAge: Int, pSalary: Int, pBonus: Int) {
        _salary = pSalary
        _bonus = pBonus
        super.init(ppName, ppAge)
    }
    
    init(ppName: String, ppAge: Int, pSalary: Int, pBonus: Int, ppV: Vehicle) {
        _salary = pSalary
        _bonus = pBonus
        super.init(ppName, ppAge, ppV)
    }
    
    override func calcEarnings() -> Double {
        return Double(salary + bonus)
    }
    
    override func getTypeOfEmployee() -> String {
        return typeOfEmployee
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Salary: $\(salary)\n  -Bonus: $\(bonus)\n  -Earnings: $\(calcEarnings())"
    }
}
