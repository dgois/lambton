import Foundation

public class FullTime : Employee {
    let typeOfEmployee = "FullTime"
    
    private var _salary: Double
    public var salary: Double {
        get {
            return _salary
        }
        
        set {
            _salary = newValue
        }
    }
    
    private var _bonus: Double
    public var bonus: Double {
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
    
    init(ppName: String, ppAge: Int, pSalary: Double, pBonus: Double) {
        _salary = pSalary
        _bonus = pBonus
        super.init(ppName, ppAge)
    }
    
    init(ppName: String, ppAge: Int, pSalary: Double, pBonus: Double, ppV: Vehicle) {
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
        return "\(super.printMyData())\n  -Salary: \(Util.toCurrencyFormatFrom(value: salary))\n  -Bonus: \(Util.toCurrencyFormatFrom(value: bonus))\n  -Earnings: \(Util.toCurrencyFormatFrom(value: calcEarnings()))"
    }
}
