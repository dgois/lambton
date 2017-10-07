public class FullTime : Employee {
    let typeOfEmployee = "FullTime"
    
    public var salary : Int
    public var bonus : Int
    
    override init() {
        salary = 0
        bonus = 0
        super.init();
    }
    
    init(ppName: String, ppAge: Int, pSalary: Int, pBonus: Int) {
        salary = pSalary
        bonus = pBonus
        super.init(ppName, ppAge)
    }
    
    init(ppName: String, ppAge: Int, pSalary: Int, pBonus: Int, ppV: Vehicle) {
        salary = pSalary
        bonus = pBonus
        super.init(ppName, ppAge, ppV)
    }
    
    override func calcEarnings() -> Double {
        return Double(salary + bonus)
    }
    
    override func getTypeOfEmployee() -> String {
        return typeOfEmployee
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Salary: $\(salary)\n  -Bonus: $\(bonus)"
    }
}
