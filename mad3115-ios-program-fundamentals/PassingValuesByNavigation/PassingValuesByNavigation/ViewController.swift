//
//  ViewController.swift
//  PassingValuesByNavigation
//
//  Created by MacStudent on 2017-10-18.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var firstNameTextField: UITextField!
    @IBOutlet weak var lastNameTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.setNavigationBarHidden(true, animated: true)
//        self.title = "First Screen"
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func submitInformation(_ sender: UIButton) {
        
        var fullname = ""
        if let firstName = firstNameTextField.text, let lastName = lastNameTextField.text {
            fullname = firstName + " " + lastName
        }
        
        let welcomeViewController = self.storyboard?.instantiateViewController(withIdentifier: "welcomeScreen") as? WelcomeViewController

        self.navigationController?.pushViewController(welcomeViewController!, animated: true)
        if let welcomeViewController = welcomeViewController {
            welcomeViewController.fullName = fullname
//            self.present(welcomeViewController, animated: true, completion: nil)
        } else {
            print("There is a problem")
        }
    }
}

