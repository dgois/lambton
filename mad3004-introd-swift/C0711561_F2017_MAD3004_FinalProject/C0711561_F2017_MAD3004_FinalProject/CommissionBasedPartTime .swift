
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
    
    override func calcEarnings() -> Double {
        return Double(hourlyRate * numberHoursWorked) + commision
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Commision: \(commision)\n  -Earnings: \(calcEarnings())"
    }
    
}
