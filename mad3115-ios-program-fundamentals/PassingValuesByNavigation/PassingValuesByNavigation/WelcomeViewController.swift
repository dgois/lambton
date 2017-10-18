//
//  WelcomeViewController.swift
//  PassingValuesByNavigation
//
//  Created by MacStudent on 2017-10-18.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class WelcomeViewController: UIViewController {
    
    var fullName: String = ""

    @IBOutlet weak var fullNameLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        fullNameLabel.text = fullName
        self.navigationController?.navigationItem.backBarButtonItem?.title = "Back to Submit"
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let thirdViewController = segue.destination as? ThirdViewController {
            thirdViewController.text = "My first segue destination parameter"
        }
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }

    @IBAction func goToThirdScreen(_ sender: UIButton!) {
        self.performSegue(withIdentifier: "thirdSegue", sender: nil)
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    
    */

}
