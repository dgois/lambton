public class Vehicle : IPrintable {

    public var make: String
    public var model: String
    public var plate: String
    
    init() {
        make = ""
        model = ""
        plate = ""
    }
    
    init(pMake: String, pModel: String, pPlate: String) {
        make = pMake
        model = pModel
        plate = pPlate
    }
    
    func getVehicleType() -> String {
        fatalError("This method must be overridden")
    }
    
    func printMyData() -> String {
        return "  -Make: \(make)\n  -Model: \(model)\n  -Plate: \(plate)"
    }

}
