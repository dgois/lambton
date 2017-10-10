
public class FixedBasedPartTime: PartTime {
    
    private var fixedAmount: Double
    
    override init() {
        fixedAmount = 0.0
        super.init()
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int, fbFixedBasedPartTime: Double) {
        self.fixedAmount = fbFixedBasedPartTime
        super.init(ppName: ppName, ppAge: ppAge, pHourlyRate: pHourlyRate, pNumberHoursWorked: pNumberHoursWorked)
    }
    
    init(ppName: String, ppAge: Int, pHourlyRate: Int, pNumberHoursWorked: Int, ppV: Vehicle, fbFixedBasedPartTime: Double) {
        self.fixedAmount = fbFixedBasedPartTime
        super.init(ppName: ppName, ppAge: ppAge, pHourlyRate: pHourlyRate, pNumberHoursWorked: pNumberHoursWorked, ppV: ppV)
    }
    
    override func calcEarnings() -> Double {
        return Double((hourlyRate * numberHoursWorked)) + fixedAmount
    }
    
    override func printMyData() -> String {
        return "  -Rate: \(hourlyRate)\n  -Hours Worked: \(numberHoursWorked)\n  -Fixed Amount:\(fixedAmount)\n  -Earnings: \(calcEarnings())"
    }
    
}
