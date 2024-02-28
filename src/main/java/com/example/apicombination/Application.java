package com.example.apicombination;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

@SpringBootApplication
@EnableScheduling
public class Application {
    private static Configuration configuration;
    private static int time_mode = 0;
    private static int time_sleep = 0;
    private static int time_sleep_long = 0;
    private static final List<String> units = new ArrayList<>();
    public static void main(String[] args){
        var context = SpringApplication
                .run(Application.class, args);
        configuration = context.getBean(Configuration.class);
        time_mode = Integer.parseInt(configuration.getTimeMode());
        time_sleep = Integer.parseInt(configuration.getTimeSleep());
        time_sleep_long = Integer.parseInt(configuration.getTimeSleepLong());
        catch_esp_in_system();
    }
    public static int get_mode(){
        return time_mode;
    }

    public static int get_time_sleep() { return time_sleep; }

    public static int get_time_sleep_long() { return time_sleep_long; }

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

    public static void catch_esp_in_system(){
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

                if (!units.contains(unit.toString())){
                    units.add(unit.toString());
                }
                System.out.println(unit);
            });

        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void control_esp_in_system(String conrolMessage){
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
            MqttMessage message = new MqttMessage(conrolMessage.getBytes());
            message.setQos(qos);
            // publish message
            client.publish("control", message);
            System.out.println(
                    ConsoleModificator.bright_green()
                            + "Control message: "
                            + ConsoleModificator.white()
                            + message
            );
            catch_esp_in_system();
            print_esp_in_system();
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void print_esp_in_system(){
        System.out.println(
                ConsoleModificator.yellow_backgound()
        + ConsoleModificator.black()
        + "--- List of esp in system ---"
        + ConsoleModificator.bright_black()
        );
        for (int i = 0; i < units.size(); i++){
            System.out.println(units.listIterator(i));
        }
        System.out.println(ConsoleModificator.none());
    }
}

