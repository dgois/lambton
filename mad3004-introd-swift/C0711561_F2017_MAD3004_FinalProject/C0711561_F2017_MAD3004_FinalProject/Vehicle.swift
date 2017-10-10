public class Vehicle : IPrintable {

    private var make: String
    private var model: String
    private var plate: String
    
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
