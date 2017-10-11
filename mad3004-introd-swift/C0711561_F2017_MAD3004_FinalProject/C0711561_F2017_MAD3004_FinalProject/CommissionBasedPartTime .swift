
public class CommissionBasedPartTime: PartTime {
    
    private var commision: Double
    
    override init() {
        commision = 0.0
        super.init()
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int, cbCommision: Double) {
        self.commision = cbCommision
        super.init(ppName: ppName, ppAge: ppAge, pHourlyRate: pHourlyRate, pNumberHoursWorked: pNumberHoursWorked)
        
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int, ppV: Vehicle, cbCommision: Double) {
        self.commision = cbCommision
        super.init(ppName: ppName, ppAge: ppAge, pHourlyRate: pHourlyRate, pNumberHoursWorked: pNumberHoursWorked, ppV: ppV)
    }
    
    private func calcSalary() -> Double {
        return Double(hourlyRate * numberHoursWorked)
    }
    
    private func calcCommision() -> Double {
        return calcSalary() * (commision / 100)
    }
    
    override func calcEarnings() -> Double {
        return calcSalary() + calcCommision()
    }
    
    override func getTypeOfEmployee() -> String {
        return super.getTypeOfEmployee() + " / Commissioned"
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Commission: \(commision)%\n  -Earnings: \(calcEarnings()) (\(calcSalary()) + \(commision)% of \(calcSalary()))"
    }
    
}
