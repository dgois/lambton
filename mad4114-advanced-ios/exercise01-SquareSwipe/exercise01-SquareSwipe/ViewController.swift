//
//  ViewController.swift
//  exercise01-SquareSwipe
//
//  Created by Denis Gois on 2018-03-21.
//  Copyright © 2018 Denis Gois. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var xConstraint: NSLayoutConstraint!
    @IBOutlet weak var yConstraint: NSLayoutConstraint!
    @IBOutlet weak var square: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        var taxa = 318
        
        let right = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.reportSwipe(_:)))
        right.direction = UISwipeGestureRecognizerDirection.right;
        view.addGestureRecognizer(right)
        
        let left = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.reportSwipe(_:)))
        left.direction = UISwipeGestureRecognizerDirection.left;
        view.addGestureRecognizer(left)
        
        let up = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.reportSwipe(_:)))
        up.direction = UISwipeGestureRecognizerDirection.up;
        view.addGestureRecognizer(up)
        
        let down = UISwipeGestureRecognizer(target: self, action: #selector(ViewController.reportSwipe(_:)))
        down.direction = UISwipeGestureRecognizerDirection.down;
        view.addGestureRecognizer(down)
        
        yConstraint.constant = (UIScreen.main.bounds.height / 2) - 50
        xConstraint.constant = (UIScreen.main.bounds.width / 2) - 50
    }
    
    @objc func reportSwipe(_ recognizer:UIGestureRecognizer) {
        DispatchQueue.main.asyncAfter(deadline: DispatchTime.now()) {
//                print("Horizontal swipe detected")
            if let gesture = recognizer as? UISwipeGestureRecognizer {
                switch gesture.direction {
                case UISwipeGestureRecognizerDirection.up:
                    print("up")
                    if (self.yConstraint.constant == ((UIScreen.main.bounds.height / 2) - 50)) {
                        self.yConstraint.constant = UIScreen.main.bounds.height / 2
                    } else {
                        self.yConstraint.constant = 0
                    }
                case UISwipeGestureRecognizerDirection.down:
                    print("down")
                    if (self.yConstraint.constant == 0) {
                        self.yConstraint.constant = (UIScreen.main.bounds.height / 2) - 50
                    } else {
                        self.yConstraint.constant = UIScreen.main.bounds.height - 100
                    }
                case UISwipeGestureRecognizerDirection.left:
                    print("left")
                    if (self.xConstraint.constant == (UIScreen.main.bounds.width - 100)) {
                        self.xConstraint.constant = (UIScreen.main.bounds.width / 2) - 50
                    } else {
                        self.xConstraint.constant = 0
                    }
                case UISwipeGestureRecognizerDirection.right:
                    print("right")
                    if (self.xConstraint.constant == 0) {
                        self.xConstraint.constant = (UIScreen.main.bounds.width / 2) - 50
                    } else {
                        self.xConstraint.constant = UIScreen.main.bounds.width - 100
                    }

                default:
                    break
                }
            }
            
        }
    }

}

