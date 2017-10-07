
public class Car: Vehicle {
    
    private let vehicleType = "Car"
    private var trunkSize: Double
    private var gear: String
    private var doorsNumber: Int

    override init() {
        trunkSize = 0.0
        gear = ""
        doorsNumber = 0
        super.init()
    }
    
    init(pMake: String, pModel: String, pPlate: String, pTrunkSize: Double, pGear: String, pDoorsNumber: Int) {
        trunkSize = pTrunkSize
        gear = pGear
        doorsNumber = pDoorsNumber
        super.init(pMake: pMake, pModel: pModel, pPlate: pPlate)
    }
    
    override func getVehicleType() -> String {
        return vehicleType
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\n  -Vehicle Type: \(vehicleType)\n  -Trunk Size: \(trunkSize)\n  -Gear: \(gear)\n  -DoorsNumber: \(doorsNumber)\n"
    }
}
