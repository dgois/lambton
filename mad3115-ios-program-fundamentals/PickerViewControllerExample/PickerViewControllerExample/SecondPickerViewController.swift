//
//  SecondPickerViewController.swift
//  PickerViewControllerExample
//
//  Created by MacStudent on 2017-10-19.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class SecondPickerViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    @IBOutlet weak var countryTechPickerView: UIPickerView!
    @IBOutlet weak var selectedCountryTechLabel: UILabel!
    
    var countries = ["Brazil", "India", "Argentina", "Italy", "Spain", "Romenia", "England", "Germany", "Canada", "Mexico"]
    var technologies = ["Java", "IOS", "Swift", "Andriod", "Clojure", "Scala", "PHP", "Oracle"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        countryTechPickerView.dataSource = self
        countryTechPickerView.delegate = self
        
        let country = countries[countryTechPickerView.selectedRow(inComponent: 0)]
        let tech = technologies[countryTechPickerView.selectedRow(inComponent: 1)]
        selectedCountryTechLabel.text = country + " - " + tech
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return countries[row]
        }
        return technologies[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return countries.count
        }
        return technologies.count
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == 0 {
            selectedCountryTechLabel.text = countries[row]
            selectedCountryTechLabel.text = countries[row] + " - " + technologies[countryTechPickerView.selectedRow(inComponent: 1)]
        } else {
            let country = countries[countryTechPickerView.selectedRow(inComponent: 0)]
            selectedCountryTechLabel.text = country + " - " + technologies[row]
        }
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
