//
//  ViewController.swift
//  PickerViewControllerExample
//
//  Created by MacStudent on 2017-10-19.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {

    var countries = ["Brazil", "India"]//, "Argentina", "Italy", "Spain", "Romenia", "England", "Germany", "Canada", "Mexico"]
    var brazilStates = ["Sao Paulo", "Rio de Janeiro", "Minas Gerais", "Porto Alegre"]
    var indiaStates = ["Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa"]
    
    var countryStates = Dictionary<String, [String]>()

    @IBOutlet weak var countryPickerView: UIPickerView!
    @IBOutlet weak var selectedCountry: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        countryStates[countries[0]] = brazilStates
        countryStates[countries[1]] = indiaStates
        
        // Do any additional setup after loading the view, typically from a nib.
        countryPickerView.dataSource = self
        countryPickerView.delegate = self
        
        let country = countries[countryPickerView.selectedRow(inComponent: 0)]
        let state = countryStates[country]![countryPickerView.selectedRow(inComponent: 1)]
        selectedCountry.text = country + " - " + state
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return countries.count
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return countries.count
        } else if component == 1 {
            return countryStates[countries[countryPickerView.selectedRow(inComponent: 0)]]!.count
        }
        return 0
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return countries[row]
        } else if component == 1 {
            return countryStates[countries[countryPickerView.selectedRow(inComponent: 0)]]![row]
        }
        return "Invalid"
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == 0 {
            selectedCountry.text = countries[row]
            countryPickerView.reloadComponent(1)
            let country = countries[countryPickerView.selectedRow(inComponent: 0)]
            let state = countryStates[country]![countryPickerView.selectedRow(inComponent: 1)]
            selectedCountry.text = country + " - " + state
        } else if component == 1 {
            selectedCountry.text = countries[countryPickerView.selectedRow(inComponent: 0)] + " - " + countryStates[countries[countryPickerView.selectedRow(inComponent: 0)]]![row]
        }
        
    }

}

