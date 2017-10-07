var fullName = [String: String]()
fullName["firstName"] = "Denis"
fullName["lastName"] = "Gois"

var address = [String: Any]()
address["street"] = "Fairview Mall Drive"
address["number"] = 5
address["city"] = "Toronto"
address["postalcode"] = "M2J 5T6"

var birthdate = [String: Int]()
birthdate["dd"] = 3
birthdate["mm"] = 7
birthdate["yy"] = 77

var student1 = [String: AnyObject]()
student1["fullname"] = fullName as AnyObject
student1["address"] = address as AnyObject
student1["birthdate"] = birthdate as AnyObject

var students = [String: AnyObject]()
students["C0711561"] = student1 as AnyObject

print(students)
