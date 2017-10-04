//: Playground - noun: a place where people can play

import UIKit

class Student {
    var name :String
    var course :Course?
    
    init(name: String) {
        self.name = name
    }
    
    func getStudentInformation() -> String {
        return "Student: \(name) Course: \(course?.name)" 
    }
}

class Course {
    var name :String
    var subject :Subject?
    
    init(name: String) {
        self.name = name
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
