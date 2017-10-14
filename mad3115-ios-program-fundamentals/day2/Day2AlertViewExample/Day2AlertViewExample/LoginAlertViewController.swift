//
//  ViewController.swift
//  Day2AlertViewExample
//
//  Created by moxDroid on 2017-10-13.
//  Copyright © 2017 moxDroid. All rights reserved.
//

import UIKit

class LoginAlertViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        //showLogin()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func btnShowLoginClick(_ sender: UIButton) {
        showLogin()
    }
    func showLogin(){
        
        let alertController = UIAlertController(title: "Title", message: "Message", preferredStyle: .alert)

        let loginAction = UIAlertAction(title: "Login", style: .default)
        { [weak alertController] _ in
            if let alertController = alertController {
                let loginTextField = alertController.textFields![0] as UITextField
                let passwordTextField = alertController.textFields![1] as UITextField
                print(loginTextField,passwordTextField)
                //login(loginTextField.text, passwordTextField.text)
            }
        }
        loginAction.isEnabled = false
        
        let forgotPasswordAction = UIAlertAction(title: "Forgot Password", style: .destructive) { _ in }
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { _ in }
        
        alertController.addTextField { textField in
            textField.placeholder = "Login"
            
            NotificationCenter.default.addObserver(forName: NSNotification.Name.UITextFieldTextDidChange, object: textField, queue: OperationQueue.main) { notification in
                loginAction.isEnabled = textField.text != ""
            }
        }
        
        alertController.addTextField { textField in
            textField.placeholder = "Password"
            textField.isSecureTextEntry = true
        }
        
    
        alertController.addAction(loginAction)
        alertController.addAction(forgotPasswordAction)
        alertController.addTextField { textField in
            textField.placeholder = "Email"
        }
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }


}

