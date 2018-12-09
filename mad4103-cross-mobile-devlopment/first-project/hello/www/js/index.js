/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);

        document.querySelectorAll("button").forEach(function (e) {
        e.addEventListener("click", function (event) {
            event.preventDefault();

            var n1 = document.querySelector("#number1"),
                n2 = document.querySelector("#number1"),
                result = document.querySelector("#result"),
                op = event.target.id;

            
            result.vale = 0;

            switch (op) {
                case "plus":
                    result.value = Number(n1.value) + Number(n2.value);
                    break;
                case "subtract":
                    result.value = Number(n1.value) - Number(n2.value);
                    break;
                case "times":
                    result.value = Number(n1.value) * Number(n2.value);
                    break;
                case "divide":
                    result.value = Number(n1.value) / Number(n2.value);
                    break;
                case "clear":
                    document.querySelector("form").reset();
                    break;
                default:
                    console.log("Invalid option");
            }

        }); 
    })
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        

    }
};

app.initialize();