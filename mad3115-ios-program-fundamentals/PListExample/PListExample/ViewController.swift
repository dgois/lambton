//
//  ViewController.swift
//  PListExample
//
//  Created by MacStudent on 2017-10-19.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
//        readInformationFromPlist()
        readPropertyList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func readInformationFromPlist() {
        if let bundlePath = Bundle.main.path(forResource: "SamplePropertyList", ofType: "plist") {
            let dictionaryPlist = NSMutableDictionary(contentsOfFile: bundlePath)
            print(dictionaryPlist!.description)
        }
    }
    
    func readPropertyList() {
        var propertyListForamt =  PropertyListSerialization.PropertyListFormat.xml //Format of the Property List.
        var plistData: [String: AnyObject] = [:] //Our data
        let plistPath: String? = Bundle.main.path(forResource: "SamplePropertyList", ofType: "plist")! //the path of the data
        let plistXML = FileManager.default.contents(atPath: plistPath!)!
        do {//convert the data to a dictionary and handle errors.
            plistData = try PropertyListSerialization.propertyList(from: plistXML, options: .mutableContainersAndLeaves, format: &propertyListForamt) as! [String:AnyObject]
            print(plistData.description)
        } catch {
            print("Error reading plist: \(error), format: \(propertyListForamt)")
        }
    }
    
    func foo {
        var array = NSMutableDictionary()
        
        var paths = NSSearchPathForDirectoriesInDomains(.documentDirectory, .userDomainMask, true) as Array
        
        let documentPath
        
        let file
    }

}

