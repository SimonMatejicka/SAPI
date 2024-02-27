package com.example.apicombination;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.lang.Math.random;

@SpringBootApplication
@EnableScheduling
public class Application {
    static Configuration configuration;
    static int time_mode = 0;
    static int time_sleep = 0;
    static int time_sleep_long = 0;
    public static void main(String[] args){
        var context = SpringApplication
                .run(Application.class, args);
        configuration = context.getBean(Configuration.class);
        time_mode = Integer.parseInt(configuration.getTimeMode());
        time_sleep = Integer.parseInt(configuration.getTimeSleep());
        time_sleep_long = Integer.parseInt(configuration.getTimeSleepLong());
        esp_in_system();
    }
    public static int get_mode(){
        return time_mode;
    }

    public static int get_time_sleep() { return time_sleep; }

    public static int get_time_sleep_long() { return time_sleep; }

    public static void esp_sleep(Integer time){
        int qos = 0;
        try {
            MqttClient client = new MqttClient(configuration.getBroker(), configuration.getClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(configuration.getUsername());
            options.setPassword(configuration.getPassword().toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            // create message and setup QoS
            MqttMessage message = new MqttMessage(time.toString().getBytes());
            message.setQos(qos);
            // publish message
            client.publish("sleep", message);
            System.out.println(
                    ConsoleModificator.green()
                            + "Message published in topic:    "
                            + ConsoleModificator.white()
                            + configuration.getTopic()
            );
            System.out.println(
                    ConsoleModificator.bright_green()
                            + "message content:               "
                            + ConsoleModificator.white()
                            + time
            );
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void zvonenie(String[] songs) {
        int i = (int) ((random()*100) % songs.length);
        int qos = 0;
        try {
            MqttClient client = new MqttClient(configuration.getBroker(), configuration.getClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(configuration.getUsername());
            options.setPassword(configuration.getPassword().toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            // create message and setup QoS
            MqttMessage message = new MqttMessage(songs[i].getBytes());
            message.setQos(qos);
            // publish message
            client.publish("zvonenie", message);
            System.out.println(
                            ConsoleModificator.green()
                            + "Message published in topic:    "
                            + ConsoleModificator.white()
                            + configuration.getTopic()
            );
            System.out.println(
                            ConsoleModificator.bright_green()
                            + "message content:               "
                            + ConsoleModificator.white()
                            + songs[i]
            );
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void esp_in_system(){
        try {
            MqttClient client = new MqttClient(configuration.getBroker(), configuration.getClientId(), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(configuration.getUsername());
            options.setPassword(configuration.getPassword().toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            // get units
            client.subscribe("units", (topic, msg) -> {
                byte[] payload = msg.getPayload();
                StringBuilder unit = new StringBuilder();
                for (byte b : payload) {
                    unit.append((char) b);
                }
                System.out.println(unit);
            });

        } catch (MqttException e) {
            e.getCause();
        }
    }
}

