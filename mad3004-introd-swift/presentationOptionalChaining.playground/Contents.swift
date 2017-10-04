//: Playground - noun: a place where people can play


class Student {
    var name :String
    var course :Course?
    
    init(name: String) {
        self.name = name
    }
    
    func getStudentInformation() -> String {
        return "Student: \(name) Course: \(course?.name ?? "Student does not have course")" 
    }
}

class Course {
    var name :String
    var subject :Subject?
	var	subjectHistory = [Subject]()
    
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

var subject = Subject(name: "Swift Basics", marks: 80)

var course = Course(name: "Mobile Development")
course.subject = subject

var student = Student(name: "John Due")
student.course = course

if student.course?.subject?.printSubjectDetails() != nil {
    print("It was possible to print the student informations")
} else {
    print("It was not possible to print")
}

//Output : It was not possible to print

var m = student.course?.subject?.marks
print(type(of: m))

//Output : Optional<Int>

if let subjectName = student.course?[0].name {
    print("The first subject name is \(subjectName).")
} else {
    print("Unable to retrieve the first subject name.")
}


