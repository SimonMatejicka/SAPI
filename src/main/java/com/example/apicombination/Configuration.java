package com.example.apicombination;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
    @Value("${mqtt.broker}")
    private  String broker;
    @Value("${mqtt.topic}")
    private String topic;
    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.clientid}")
    private String clientId;

    public String getBroker() {
        return broker;
    }

    public String getTopic() {
        return topic;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getClientId() {
        return clientId;
    }
}
