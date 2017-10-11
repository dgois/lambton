
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
    
    private func calcSalary() -> Double {
        return Double(hourlyRate * numberHoursWorked)
    }
    
    override func calcEarnings() -> Double {
        return calcSalary() + fixedAmount
    }
    
    override func getTypeOfEmployee() -> String {
        return super.getTypeOfEmployee() + " / Fixed Amt"
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Fixed Amount: \(Util.getInstant().toCurrencyFormatFrom(value: fixedAmount))\n  -Earnings: \(Util.getInstant().toCurrencyFormatFrom(value: calcEarnings())) (\(calcSalary()) + \(fixedAmount))"
    }
    
}
