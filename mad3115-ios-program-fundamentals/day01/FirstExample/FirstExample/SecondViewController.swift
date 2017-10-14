//
//  SecondViewController.swift
//  FirstExample
//
//  Created by MacStudent on 2017-10-13.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //MARKS: Actions
    @IBAction func showMyAlert(_ sender: UIButton) {
//        let alert = UIAlertController(title: "Fail", message: "You failed to logged In", preferredStyle: UIAlertControllerStyle.alert)
        
        let alert = UIAlertController(title: "Message", message: "Welcome to iOS Programming", preferredStyle: UIAlertControllerStyle.alert )
        
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {
            _ in print("Print OK")
        }))
        alert.addAction(UIAlertAction(title: "Confirm", style: UIAlertActionStyle.default, handler: nil))
        alert.addAction(UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil))
        alert.addAction(UIAlertAction(title: "Destructive", style: UIAlertActionStyle.destructive, handler: nil))
        
        
        
        self.present(alert, animated: true, completion: nil)
    }
    
    @IBAction func showLoginAlertScreen(_ sender: UIButton) {
        
        let alertController = UIAlertController(title: "Title", message: "Message", preferredStyle: UIAlertControllerStyle.alert)
        
        alertController.addTextField { (loginTextField) in
            loginTextField.placeholder = "Login"
            
            NotificationCenter.default.addObserver(forName: NSNotification.Name.UITextFieldTextDidChange, object: loginTextField, queue: OperationQueue.main, using: { (notification) in
                loginTextField.isEnabled = loginTextField.text != ""
            })
        }
        
        alertController.addTextField { (passTextField) in
            passTextField.placeholder = "Password"
            passTextField.isSecureTextEntry = true
        }
        
        alertController.addTextField { (emailTextField) in
            emailTextField.placeholder = "Email"
        }
        
        let loginAction = UIAlertAction(title: "OK", style: .default) {
            [weak alertController] _ in
            if let alertController = alertController {
                let loginTextField = alertController.textFields![0] as UITextField
                let passTextField = alertController.textFields![1] as UITextField
                
                print(loginTextField.text!, passTextField.text!)
            }
        }
        loginAction.isEnabled = false
        alertController.addAction(loginAction)
        
        alertController.addAction(UIAlertAction(title: "Forget Password", style: .destructive, handler: nil))
        alertController.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
        
        self.present(alertController, animated: true, completion: nil)
    }
}
