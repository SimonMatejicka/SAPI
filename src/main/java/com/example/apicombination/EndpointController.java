package com.example.apicombination;


import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/control")
public class EndpointController {
    @GetMapping("/{mac}")
    public String getDiagnose(@PathVariable String mac) throws MqttException {
        Application.esp_diagnose(mac);
        return Application.esp_diagnose_catch(mac);
    }
}
