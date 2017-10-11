//
//  Util.swift
//  C0711561_F2017_MAD3004_FinalProject
//
//  Created by MacStudent on 2017-10-11.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import Foundation

class Util {
    static func toCurrencyFormatFrom(value: Double) -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        
        return formatter.string(from: value as NSNumber) ?? "$0.00"
    }
}
