//
//  ViewController.swift
//  four-day
//
//  Created by MacStudent on 2017-10-17.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    // MARK: - Parameters
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var segFelines: UISegmentedControl!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var stepperLabel: UILabel!
    @IBOutlet weak var franceDateTime: UILabel!
    @IBOutlet weak var indiaDateTime: UILabel!
    @IBOutlet weak var egiptDateTime: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.segFelines.selectedSegmentIndex = 1
        self.changeTitleName(segFelines)
        self.segFelines.insertSegment(withTitle: "Jaguar", at: 3, animated: true)
        imageView.image = UIImage(named: "tiger")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK - Actions
    @IBAction func changeTitleName(_ sender: UISegmentedControl) {
        
        if sender.selectedSegmentIndex == 0 {
            imageView.image = UIImage(named: "lion")
            lblTitle.text = "Hi, Lion!"
        } else if sender.selectedSegmentIndex == 1 {
            imageView.image = UIImage(named: "tiger")
            lblTitle.text = "Hi, Tiger!"
        } else if sender.selectedSegmentIndex == 2 {
            imageView.image = UIImage(named: "cat")
            lblTitle.text = "Hi, Cat!"
        } else if sender.selectedSegmentIndex == 3 {
            imageView.image = UIImage(named: "jaguar")
            lblTitle.text = "Hi, Jaguar!"
        }
    }
    
    @IBAction func changeStep(_ sender: UIStepper) {
        stepperLabel.text = String(sender.value)
    }
    
    @IBAction func changeDate(_ sender: UIDatePicker) {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        formatter.timeStyle = .long

        formatter.locale = Locale(identifier: "fr_FR")
        franceDateTime.text = formatter.string(from: sender.date)
        
        formatter.locale = Locale(identifier: "bn_BN")
        indiaDateTime.text = formatter.string(from: sender.date)
        
        formatter.locale = Locale(identifier: "ar_EG")
        egiptDateTime.text = formatter.string(from: sender.date)
    }
}

