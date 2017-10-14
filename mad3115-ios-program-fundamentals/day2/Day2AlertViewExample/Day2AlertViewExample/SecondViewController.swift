//
//  SecondViewController.swift
//  Day2AlertViewExample
//
//  Created by moxDroid on 2017-10-13.
//  Copyright © 2017 moxDroid. All rights reserved.
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
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func showMyAlert(_ sender: UIButton) {
        
        //Create AlertController Object
        let alert  =
            UIAlertController(title: "Message", message: "Welcome to iOS Programming", preferredStyle: UIAlertControllerStyle.actionSheet)
        
        //OK Button add
        let actionOk = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil)
        alert.addAction(actionOk)
        
        //Confirm Button add
        let actionConfirm = UIAlertAction(title: "Confirm", style: UIAlertActionStyle.default,
                                          handler:
            {
                _ in print("Pritesh")
                print("Confirm")
                
            })
        alert.addAction(actionConfirm)
        
        //Cancel Button add
        let actionCancel = UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil)
        alert.addAction(actionCancel)
        
        //Retry Button add
        let actionRetry = UIAlertAction(title: "Retry", style: UIAlertActionStyle.destructive, handler: nil)
        alert.addAction(actionRetry)
        
        //Present Alert Dialog to the user
        self.present(alert, animated: true, completion: nil)
    }
    
}
