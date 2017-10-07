//: Playground - noun: a place where people can play
func getFullName(first: String, last: String) -> [String: String] {
    var fullName = [String: String]()
    fullName["firstName"] = first
    fullName["lastName"] = last
    return fullName
}
var fullName = getFullName(first: "Denis", last: "Gois")

func getFullAdress(street: String, number: Int, city: String, postalcode: String) -> [String: Any] {
    var address = [String: Any]()
    address["street"] = street
    address["number"] = number
    address["city"] = city
    address["postalcode"] = postalcode
    
    return address
}

var fullAddress = getFullAdress(street: "Fairview Mall Drive",
                                number: 5,
                                city: "Toronto",
                                postalcode: "M2J 5T6")

func getBirthdate(dd: Int, mm: Int, yy: Int) -> [String: Int]{
    var birthdate = [String: Int]()
    birthdate["dd"] = dd
    birthdate["mm"] = mm
    birthdate["yy"] = yy
    
    return birthdate
}
var birthdate = getBirthdate(dd: 03, mm: 05, yy: 88)

func getStudent(studentId: String, fullname: [String: AnyObject], address: [String: AnyObject], birthdate: [String: AnyObject]) -> [String: AnyObject] {
    var info = [String: AnyObject]()
    info["studentId"] = studentId as AnyObject
    info["fullname"] = fullName as AnyObject
    info["address"] = address as AnyObject
    info["birthdate"] = birthdate as AnyObject

    return info
}

var students = [String: [String: AnyObject]]()

students["C0711561"] = getStudent(studentId: "C0711561",
                                  fullname: fullName as [String: AnyObject],
                                  address: fullAddress as [String: AnyObject],
                                  birthdate: birthdate as [String: AnyObject])

students["C0711562"] = getStudent(studentId: "C0711561",
                                  fullname: fullName as [String: AnyObject],
                                  address: fullAddress as [String: AnyObject],
                                  birthdate: birthdate as [String: AnyObject])

print(students["C0711561"])
