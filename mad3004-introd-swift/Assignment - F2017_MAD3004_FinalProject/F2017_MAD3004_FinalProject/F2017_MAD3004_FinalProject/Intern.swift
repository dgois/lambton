public class Intern : Employee {
    public var schoolName : String
    
    override init() {
        schoolName = ""
        super.init()
    }
    
    init(pName: String, pAge: Int, pSchool: String) {
        schoolName = pSchool
        super.init(pName, pAge)
    }
    
    init(pName: String, pAge: Int, pSchool: String, ppV: Vehicle) {
        schoolName = pSchool
        super.init(pName, pAge, ppV)
    }

    
    override func printMyData() {
        super.printMyData()
        print ("School Name: \(schoolName)")
 
    }
    
}
