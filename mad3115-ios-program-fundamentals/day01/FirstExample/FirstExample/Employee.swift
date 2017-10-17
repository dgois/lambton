//
//  Employee.swift
//  FirstExample
//
//  Created by MacStudent on 2017-10-16.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import Foundation

class Employee {
    
    static var auto_increment_id = 0
    static var employees = Dictionary<Int, Employee>()
    
    var id: Int
    var name: String
    var salary: Decimal
    var birthDate: Date
    
    init(_ name: String, _ salary: Decimal, _ birthDate: Date) {
        Employee.auto_increment_id += 1
        self.id = Employee.auto_increment_id
        self.name = name
        self.salary = salary
        self.birthDate = birthDate
    }
    
    static func save(employee: Employee) {
        employees[employee.id] = employee
    }
    
    static func getBy(id: Int) -> Employee? {
        if let emp = employees[id] {
            return emp
        }
        return nil
    }
    
    static func getAllEmployees() -> [Int:Employee] {
        return employees
    }
    
    static func remove(emp: Employee) -> Bool {
        return employees.removeValue(forKey: emp.id) != nil
    }
    
    static func update(emp: Employee) -> Bool {
        if employees[emp.id] != nil {
            employees[emp.id] = emp
            return true
        }
        return false
    }
}
