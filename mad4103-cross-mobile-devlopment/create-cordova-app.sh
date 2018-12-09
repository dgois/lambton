#!/bin/bash

clear

echo "Creating the Cordova Project"
cordova create $1 $2 "$3"

echo "Change to the new project folder"
cd $1

echo "Adding Platforms"
cordova platform add android ios browser

echo "Adding Plugins"
cordova plugin add cordova-plugin-console
cordova plugin add cordova plugin add cordova-plugin-dialogs
cordova plugin add cordova-plugin-device

echo
echo "Finished!"
