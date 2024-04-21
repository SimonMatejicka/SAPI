package com.example.apicombination;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.random;

@SpringBootApplication
@EnableScheduling
public class Application {
    private static Configuration configuration;
    private static MqttClient client;
    private static int time_mode = 0;
    private static int time_sleep = 0;
    private static int time_sleep_long = 0;
    private static final Map<String, Boolean> units = new HashMap<>();

    private static void MQTT_connecting() throws MqttException {
        client = new MqttClient(configuration.getBroker(), configuration.getClientId(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(configuration.getUsername());
        options.setPassword(configuration.getPassword().toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);
        // connect
        client.connect(options);
    }


    public static void main(String[] args) throws MqttException {
        var context = SpringApplication
                .run(Application.class, args);
        configuration = context.getBean(Configuration.class);
        time_mode = Integer.parseInt(configuration.getTimeMode());
        time_sleep = Integer.parseInt(configuration.getTimeSleep());
        time_sleep_long = Integer.parseInt(configuration.getTimeSleepLong());
        MQTT_connecting();
        catch_esp_in_system();
    }

    public static int get_mode(){
        return time_mode;
    }

    public static int get_time_sleep() { return time_sleep; }

    public static int get_time_sleep_long() { return time_sleep_long; }

    public static void esp_diagnose(String mac) throws MqttException {
        final String[] _diagnose = new String[1];
        int qos = 0;
        MQTT_connecting();
        MqttMessage message = new MqttMessage("diagnose".getBytes());
        message.setQos(qos);
        // publish message
        client.publish(mac, message);
        client.disconnect();
    }
    public static String esp_diagnose_catch(String mac) throws MqttException {
        final String[] _diagnose = new String[1];
        int qos = 0;
        MQTT_connecting();
        MqttMessage message = new MqttMessage("diagnose".getBytes());
        message.setQos(qos);
        // publish message
        client.subscribe("diagnose/" + mac, (topic, msg) -> {
            byte[] payload = msg.getPayload();
            StringBuilder stats = new StringBuilder();
            for (byte b : payload) {
                stats.append((char) b);
            }
            // TODO parse json
            _diagnose[0] = stats.toString();
        });
        client.disconnect();
        return _diagnose[0];
    }
    public static void esp_sleep(Integer time){
        int qos = 0;
        try {
            MQTT_connecting();
            // create message and setup QoS
            MqttMessage message = new MqttMessage(time.toString().getBytes());
            message.setQos(qos);
            // publish message
            client.publish("sleep", message);
            client.disconnect();
            // DEBUG
            /*
            System.out.println(
                    ConsoleModificator.blue()
                            + "Message published in topic:    "
                            + ConsoleModificator.white()
                            + configuration.getTopic()
            );
            System.out.println(
                    ConsoleModificator.bright_blue()
                            + "message content:               "
                            + ConsoleModificator.white()
                            + time
            );
            */
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void zvonenie(String[] songs) {
        int i = (int) ((random()*100) % 31 /*songs.length*/);
        String song = "-";
        if (i < 9){
            song += "0";
        }
        song += (i+1) + ".mp3";
        // System.out.println(song);
        int qos = 0;
        try {
            MQTT_connecting();
            // create message and setup QoS
            // MqttMessage message = new MqttMessage(songs[i].getBytes());
            MqttMessage message = new MqttMessage(song.getBytes());
            message.setQos(qos);
            // publish message
            client.publish("zvonenie", message);
            client.disconnect();
            // DEBUG
            /*
            System.out.println(
                            ConsoleModificator.blue()
                            + "Message published in topic:    "
                            + ConsoleModificator.white()
                            + configuration.getTopic()
            );
            System.out.println(
                            ConsoleModificator.bright_blue()
                            + "message content:               "
                            + ConsoleModificator.white()
                            + songs[i]
            );
             */
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void catch_esp_in_system(){
        try {
            MQTT_connecting();
            // get units
            client.subscribe("units", (topic, msg) -> {
                byte[] payload = msg.getPayload();
                StringBuilder unit = new StringBuilder();
                for (byte b : payload) {
                    unit.append((char) b);
                }

                if (!units.containsKey(unit.toString())){
                    units.put(unit.toString(), true);
                }
                else {
                    units.computeIfPresent(unit.toString(), (k,v) -> v = true);
                }
            });
            client.disconnect();
        } catch (MqttException e) {
            e.getCause();
        }
    }

    public static void control_esp_in_system(String conrolMessage){
        int qos = 0;
        for (Map.Entry<String, Boolean> unit : units.entrySet()) {
            unit.setValue(false);
        }
        try {
            MQTT_connecting();
            // create message and setup QoS
            MqttMessage message = new MqttMessage(conrolMessage.getBytes());
            message.setQos(qos);
            // publish message
            client.publish("control", message);
            client.disconnect();
            // DEBUG
            /*
            System.out.println(
                    ConsoleModificator.bright_blue()
                            + "Control message: "
                            + ConsoleModificator.white()
                            + message
            );
            */
        } catch (MqttException e) {
            e.getCause();
        }
    }

    // DEBUG
    public static void print_esp_in_system(){
        System.out.println(
                //ConsoleModificator.move_cursor_up( )
                 ConsoleModificator.cyan()
                + "------------  List of esp in system  ------------"
        );
        String out;
        for (Map.Entry<String, Boolean> unit : units.entrySet()) {
            if (unit.getValue()){
                out = ConsoleModificator.bright_green();
            }
            else {
                out = ConsoleModificator.bright_red();
            }
            System.out.println(out + unit.getKey());
        }
        System.out.println(ConsoleModificator.none() + "                     ");
    }
}

