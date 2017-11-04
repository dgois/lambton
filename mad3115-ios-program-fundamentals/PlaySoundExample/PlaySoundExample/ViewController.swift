//
//  ViewController.swift
//  PlaySoundExample
//
//  Created by MacStudent on 2017-11-03.
//  Copyright © 2017 MacStudent. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {
    
    @IBOutlet weak var progressAudio: UIProgressView!
    @IBOutlet weak var volumeSlider: UISlider!
    @IBOutlet weak var currentTime: UILabel!
    var player: AVAudioPlayer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        prepareThePlayer()
        configureProgressView()
        
        volumeSlider.setValue((player?.volume)!, animated: true)
    }
    
    fileprivate func configureProgressView(){
        Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(track), userInfo: nil, repeats: true)
    }
    
    fileprivate func prepareThePlayer() {
        let path = Bundle.main.path(forResource: "mozart-horn-concerto4-3-rondo", ofType: "mp3")!
        let url = URL(fileURLWithPath: path)
        
        do {
            player = try AVAudioPlayer(contentsOf: url)
        } catch {
            print("failed: \(String(describing: error))")
        }
    }
    
    @objc func track() {
        progressAudio.progress = Float((player?.currentTime)! / (player?.duration)!)
        let seconds = player?.currentTime
        let minutes = Float(seconds!) / 60.0
        currentTime.text = "\(minutes.rounded()):\(seconds!.rounded())"
    }
    
    @IBAction func play(_ sender: UIButton) {
        player?.play()
    }
    
    @IBAction func stop(_ sender: UIButton) {
        player?.stop()
    }
    
    @IBAction func changeVolume(_ sender: UISlider) {
        player?.volume = volumeSlider.value
    }
}

