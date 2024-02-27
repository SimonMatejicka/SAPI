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
    @Value("${time.mode}")
    private String timeMode;
    @Value("${time.sleep}")
    private String timeSleep;
    @Value("${time.sleep.long}")
    private String timeSleepLong;


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

    public String getTimeMode() { return timeMode; }

    public String getTimeSleep() { return timeSleep; }

    public String getTimeSleepLong() { return timeSleepLong; }
}
