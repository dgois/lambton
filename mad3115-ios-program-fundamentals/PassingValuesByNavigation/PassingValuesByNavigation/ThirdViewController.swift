//
//  ThirdViewController.swift
//  PassingValuesByNavigation
//
//  Created by MacStudent on 2017-10-18.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController {

    var text = ""
    @IBOutlet weak var textThirdViewController: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        textThirdViewController.text = text
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

}
