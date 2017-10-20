//
//  CreateEmployeeViewController.swift
//  TableViewExample
//
//  Created by MacStudent on 2017-10-19.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class CreateEmployeeViewController: UIViewController {
    
    @IBOutlet weak var txtEmpName: UITextField!
    @IBOutlet weak var txtEmpSalary: UITextField!
    
    var employees = [Employee]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
    }
    
    @IBAction func saveEmployee(_ sender: UIButton) {
        if let name = txtEmpName.text, let salary = txtEmpSalary.text {
            employees.append(Employee(name: name, salary: Double(salary)!))
        }
    }
}

class Employee {
    
    var name: String
    var salary: Double
    
    init(name: String, salary: Double) {
        self.name = name
        self.salary = salary
    }
}
