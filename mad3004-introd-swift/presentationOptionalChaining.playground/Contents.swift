//-- Model for the example

class Student {
    var name :String
    var course :Course?
    
    init(name: String) {
        self.name = name
    }
    
    func getStudentInformation() -> String? {
        if let courseName = course?.name {
            return "Name: \(name) and Course: \(courseName)"
        } else {
            return nil
        }
    }
}

class Course {
    var name :String
    var subject :Subject?
	var	subjectHistory = [Subject(name: "Swift Basics", marks: 80)]
    
    init(name: String) {
        self.name = name
    }
	
	subscript(i: Int) -> Subject {
        get {
            return subjectHistory[i]
        }
        set {
            subjectHistory[i] = newValue
        }
    }
}

class Subject {
    var name :String
    var marks :Int
    
    init(name :String, marks :Int) {
        self.name = name
        self.marks = marks
    }
    
    func printSubjectDetails() {
        print("Subject Name: \(name). Marks: \(marks)")
    }
}

//-- Instanciate the objects

var subject = Subject(name: "Swift Basics", marks: 80)

var course = Course(name: "Mobile Development")
course.subject = subject

var student = Student(name: "John Due")
student.course = course


//-- Calling Through Optional Chaining

if student.course?.subject?.printSubjectDetails() != nil {
    print("It was possible to print the student informations")
} else {
    print("It was not possible to print")
}

//Output : It was not possible to print when course or subject is nil or not set yet

var m = student.course?.subject?.marks
print(type(of: m))

//Output : Optional<Int>


//-- Accessing Subscripts Through Optional Chaining

if let subjectName = student.course?[0].name {
    print("The first subject name is \(subjectName).")
} else {
    print("Unable to retrieve the first subject name.")
}

//Output : The first subject name is Swift Basics.


//-- Accessing Subscripts of Optional Type

var studentMarks = ["Tom": [86, 82, 84], "Mary": [79, 94, 81]]
studentMarks["Tom"]?[0] = 91
studentMarks["Mary"]?[0] += 1
studentMarks["Brian"]?[0] = 72

print(type(of: studentMarks["Brian"]))

//Output : Optional<Array<Int>>

print(studentMarks)

//Output : ["Tom": [91, 82, 84], "Mary": [80, 94, 81]]


//-- Methods with Optional Return Values

if let studentInformation = student.getStudentInformation() {
    print("John's informations: \(studentInformation).")
}
//Output "John's information Student: John Due Course: Mobile Development."
