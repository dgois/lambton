
public class Motorcycle: Vehicle {
    
    private let vehicleType = "Motorcycle"
    private var cylinderCapacity: Int
    private var type: String //street, off-road, and dual purpose
    private var ridingPosture: String //sport, standard, cruiser
    
    override init() {
        cylinderCapacity = 0
        type = ""
        ridingPosture = ""
        super.init()
    }
    
    init(pMake: String, pModel: String, pPlate: String, pCylinderCapacity: Int, pType: String, pRidingPosture: String) {
        cylinderCapacity = pCylinderCapacity
        type = pType
        ridingPosture = pRidingPosture
        super.init(pMake: pMake, pModel: pModel, pPlate: pPlate)
    }
    
    override func getVehicleType() -> String {
        return vehicleType
    }
    
    override func printMyData() -> String {
        return "\(super.printMyData())\nVehicle Type: \(vehicleType)\nCylinder Capacity: \(cylinderCapacity)\nType: \(type)\nRiding Posture: \(ridingPosture)\n"
    }
}
