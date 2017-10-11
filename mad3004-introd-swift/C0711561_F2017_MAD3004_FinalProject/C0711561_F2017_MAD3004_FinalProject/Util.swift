//
//  Util.swift
//  C0711561_F2017_MAD3004_FinalProject
//
//  Created by MacStudent on 2017-10-11.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import Foundation

class Util {
    
    private static var instant: Util?
    
    private init(){}
    
    public static func getInstant() -> Util {
        if instant != nil{
            return instant!
        }else{
            instant = Util()
            return instant!
        }
    }
    
    public func toCurrencyFormatFrom(value: Double) -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        
        return formatter.string(from: value as NSNumber) ?? "$0.00"
    }
}
