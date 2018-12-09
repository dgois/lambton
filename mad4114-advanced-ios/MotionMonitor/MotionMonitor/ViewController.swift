//
//  ViewController.swift
//  MotionMonitor
//
//  Created by Denis Gois on 2018-03-28.
//  Copyright © 2018 Denis Gois. All rights reserved.
//

import UIKit
import CoreMotion

class ViewController: UIViewController {
    
    @IBOutlet var gyroscopeLabel: UILabel!
    @IBOutlet var accelerometerLabel: UILabel!
    @IBOutlet var attitudeLabel: UILabel!
    
    private let motionManager = CMMotionManager()
    private let queue = OperationQueue()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        if motionManager.isDeviceMotionAvailable {
            motionManager.deviceMotionUpdateInterval = 0.1
            motionManager.startDeviceMotionUpdates(to: queue, withHandler: { (motion:CMDeviceMotion?, error:Error?) in
                if let motion = motion {
                    let rotationRate = motion.rotationRate
                    let gravity = motion.gravity
                    let userAcc = motion.userAcceleration
                    let attitude = motion.attitude
                    let gyroscopeText =
                        String(format: "Rotation Rate:\n-----------------\n" +
                            "x: %+.2f\ny: %+.2f\nz: %+.2f\n", rotationRate.x, rotationRate.y, rotationRate.z)
                    let acceleratorText =
                        String(format: "Acceleration:\n---------------\n" +
                            "Gravity x: %+.2f\t\tUser x: %+.2f\n" + "Gravity y: %+.2f\t\tUser y: %+.2f\n" + "Gravity z: %+.2f\t\tUser z: %+.2f\n",
                               gravity.x, userAcc.x, gravity.y,
                               userAcc.y, gravity.z,userAcc.z)
                    let attitudeText =
                        String(format: "Attitude:\n----------\n" + "Roll: %+.2f\nPitch: %+.2f\nYaw:%+.2f\n",
                            attitude.roll, attitude.pitch, attitude.yaw)

                    DispatchQueue.main.async {
                        self.gyroscopeLabel.text = gyroscopeText
                        self.accelerometerLabel.text = acceleratorText
                        self.attitudeLabel.text = attitudeText
                    }
                }
            })
        } else {
            print("Does not have sensors")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

