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
public class ApiCombinationApplication {
    static Configuration configuration;
    public static void main(String[] args){
        var context = SpringApplication
                .run(ApiCombinationApplication.class, args);
        configuration = context.getBean(Configuration.class);
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
            client.publish(configuration.getTopic(), message);
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
            // disconnect
            client.disconnect();
            // close client
            client.close();
        } catch (MqttException e) {
            System.out.println(e);
        }
    }
}

