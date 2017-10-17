//
//  EmployeeCreateViewController.swift
//  FirstExample
//
//  Created by MacStudent on 2017-10-16.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class EmployeeCreateViewController: UIViewController {
    
    let formatter = NumberFormatter()
    let dateFormatter = DateFormatter()

    // MARK: - Text fields
    @IBOutlet weak var txtName: UITextField!
    @IBOutlet weak var txtBirthDate: UITextField!
    @IBOutlet weak var txtSalary: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Actions
    @IBAction func saveEmployee(_ sender: UIButton) {
        dateFormatter.dateFormat = "dd-MM-yyyy"
        
        let name = txtName.text ?? ""
        let salary = formatter.number(from: txtSalary.text ?? "0")
        let birthDate = dateFormatter.date(from: txtBirthDate.text ?? "")
        
        let newEmployee = Employee(name, salary!.decimalValue, birthDate!)
        
        Employee.save(employee: newEmployee)
        
        for e in Employee.employees.values {
            print("Employee: \(e.id) - \(e.name) - \(e.birthDate) - \(e.salary)")
        }
        
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let empViewController = storyBoard.instantiateViewController(withIdentifier: "listEmployeesScreen") as! EmployeeTableViewController
        self.present(empViewController, animated: true, completion: nil)
        
    }

}
