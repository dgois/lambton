public class Intern : Employee {
    
    private var schoolName : String
    private var fixedSalary: Double
    
    override init() {
        schoolName = ""
        fixedSalary = 0.0
        super.init()
    }
    
    init(pName: String, pAge: Int, pSchool: String, pFixedSalary: Double) {
        schoolName = pSchool
        fixedSalary = pFixedSalary
        super.init(pName, pAge)
    }
    
    init(pName: String, pAge: Int, pSchool: String, ppV: Vehicle, pFixedSalary: Double) {
        schoolName = pSchool
        fixedSalary = pFixedSalary
        super.init(pName, pAge, ppV)
    }

    override func getTypeOfEmployee() -> String {
        return "Intern"
    }
    
    override func calcEarnings() -> Double {
        return fixedSalary
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -School Name: \(schoolName)\n  -Earnings: $\(calcEarnings())"
    }
    
}
