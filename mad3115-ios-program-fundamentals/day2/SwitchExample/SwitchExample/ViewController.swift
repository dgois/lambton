//
//  ViewController.swift
//  SwitchExample
//
//  Created by MacStudent on 2017-10-13.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var switchStateTextField: UITextField!
    @IBOutlet weak var switchField: UISwitch!
    @IBOutlet weak var sliderLabel: UILabel!
    @IBOutlet weak var progressField: UIProgressView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        sliderLabel.text = "It will show slider percentage here"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func fillTextField(_ sender: UISwitch) {
        if switchField.isOn {
            switchStateTextField.text = "Change switch to ON"
        } else if !switchField.isOn {
            switchStateTextField.text = "Change switch to OFF"
        }
    }
    
    @IBAction func changeSwitchState(_ sender: UIButton) {
        if switchField.isOn {
            switchField.setOn(false, animated: true)
        } else {
            switchField.setOn(true, animated: true)
        }
        
        fillTextField(switchField)
    }
    
    @IBAction func sliderStateChange(_ sender: UISlider) {
        let percentage = sender.value * 100
        sliderLabel.text = "\(Int(percentage))%"
        progressField.progress = sender.value
    }
}

