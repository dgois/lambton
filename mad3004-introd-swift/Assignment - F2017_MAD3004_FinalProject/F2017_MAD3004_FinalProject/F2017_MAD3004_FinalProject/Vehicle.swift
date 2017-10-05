public class Vehicle {
    public var make: String
    public var model: String
    
    init() {
        make = ""
        model = ""
    }
    
    init(pMake: String, pModel: String) {
        make = pMake
        model = pModel
    }
}
