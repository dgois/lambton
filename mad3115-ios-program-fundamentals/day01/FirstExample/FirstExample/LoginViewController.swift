//
//  ViewController.swift
//  FirstExample
//
//  Created by MacStudent on 2017-10-12.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    
    @IBOutlet weak var txtUserName: UITextField!
    @IBOutlet weak var txtUserPass: UITextField!
    @IBOutlet weak var switchRememberMe: UISwitch!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        switchRememberMe.isOn = true
        if switchRememberMe.isOn {
            txtUserName.text = "admin@admin.com"
            txtUserPass.text = "admin"
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func loginTouchUp(_ sender: UIButton) {
        if let userName = txtUserName.text, let password = txtUserPass.text {
            if (userName == "admin@admin.com" && password == "admin") {
                let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                let empViewController = storyBoard.instantiateViewController(withIdentifier: "listEmployeesScreen") as! EmployeeTableViewController
                self.present(empViewController, animated: true, completion: nil)
            } else {
                let alertController = UIAlertController(title: "Login Error", message: "User or password is wrong", preferredStyle: .alert)
                
                alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
                
                self.present(alertController, animated: true, completion: nil)
            }
        }
    }
    
    @IBAction func rememberMe(_ sender: UISwitch) {
        if sender.isOn {
            txtUserName.text = "admin@admin.com"
            txtUserPass.text = "admin"
        }
    }
}

