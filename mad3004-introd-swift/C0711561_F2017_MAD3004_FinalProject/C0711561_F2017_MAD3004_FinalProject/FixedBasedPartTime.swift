
public class FixedBasedPartTime: PartTime {
    
    var fixedAmount: Double
    
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
    
}
