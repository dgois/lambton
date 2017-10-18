//
//  ViewController.swift
//  UserDefaultExample
//
//  Created by MacStudent on 2017-10-17.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var userField: UITextField!
    @IBOutlet weak var passField: UITextField!
    @IBOutlet weak var switchRememberMe: UISwitch!
    
    var myUserDefault: UserDefaults!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        myUserDefault = UserDefaults.standard
        if let userName = myUserDefault.value(forKey: "userName") {
            userField.text = userName as? String
        }
        
        if let password = myUserDefault.value(forKey: "userPassword") {
            passField.text = password as? String
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func login(_ sender: UIButton) {
        
        if userField.text == "admin" && passField.text == "admin" {
            let alert = UIAlertController(title: "Successful Login", message: "You have successfully login", preferredStyle: .alert)
            
            let successOkAction = UIAlertAction(title: "OK", style: .default, handler: {
                _ in
                let storyBoard = UIStoryboard(name: "Main", bundle: nil)
                let welcomeViewController = storyBoard.instantiateViewController(withIdentifier: "welcomeView") as! WelcomeViewController
                self.present(welcomeViewController, animated: true, completion: nil)
            })
            alert.addAction(successOkAction)
            self.present(alert, animated: true, completion: nil)
            
            configureRememberMe()
        
        } else {
            let alert = UIAlertController(title: "Fail Login", message: "User/Pass is wrong", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    fileprivate func configureRememberMe() {
        if switchRememberMe.isOn {
            myUserDefault.set(userField.text, forKey: "userName")
            myUserDefault.set(passField.text, forKey: "userPassword")
        } else {
            myUserDefault.removeObject(forKey: "userName")
            myUserDefault.removeObject(forKey: "userPassword")
        }
    }
}

