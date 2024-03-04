package com.example.apicombination;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {
    // DEBUG
    //private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    static String[] songs = {
            "ta_nase.mp3",
            "jozin.mp3",
            "rickroll.mp3",
            "ta_nase.mp3"
    };

    // hodina 0.
    @Scheduled(cron = "0 05 07 * * MON-FRI")
    @Scheduled(cron = "0 50 07 * * MON-FRI")
    // hodina 1.
    @Scheduled(cron = "0 55 07 * * MON-FRI")
    @Scheduled(cron = "0 40 08 * * MON-FRI")
    // hodina 2.
    @Scheduled(cron = "0 45 08 * * MON-FRI")
    @Scheduled(cron = "0 30 09 * * MON-FRI")
    // hodina 3.
    @Scheduled(cron = "0 40 09 * * MON-FRI")
    @Scheduled(cron = "0 25 10 * * MON-FRI")
    // hodina 4.
    @Scheduled(cron = "0 40 10 * * MON-FRI")
    @Scheduled(cron = "0 25 11 * * MON-FRI")
    // hodina 5.
    @Scheduled(cron = "0 30 11 * * MON-FRI")
    @Scheduled(cron = "0 15 12 * * MON-FRI")
    // hodina 6.
    @Scheduled(cron = "0 20 12 * * MON-FRI")
    @Scheduled(cron = "0 05 13 * * MON-FRI")
    // hodina 7.
    @Scheduled(cron = "0 35 13 * * MON-FRI")
    @Scheduled(cron = "0 20 14 * * MON-FRI")
    // hodina 8.
    @Scheduled(cron = "0 25 14 * * MON-FRI")
    @Scheduled(cron = "0 10 15 * * MON-FRI")

    // DEBUG
    // @Scheduled(cron = "${cron.expression}")
    @Scheduled(cron = "0 */2 * * * *") // every minute, when seconds are 0
    //@Scheduled(cron = "0 33 14 * * *")
    private void start_zvonenie() {
        if (Application.get_mode() == 0){
            // DEBUG
            //System.out.println("long");
            /*
            System.out.println(
                    ConsoleModificator.bright_green()
                            + "Time of execution:          "
                            + ConsoleModificator.dark_white()
                            + formatter.format(LocalDateTime.now())
                            + ConsoleModificator.none()
            );
             */
            Application.zvonenie(songs);
        }
    }

    // need to be changed
    @Scheduled(cron = "0 05 07 * * MON-FRI")
    @Scheduled(cron = "0 50 07 * * MON-FRI")
// hodina 1.
    @Scheduled(cron = "0 55 07 * * MON-FRI")
    @Scheduled(cron = "0 40 08 * * MON-FRI")
// hodina 2.
    @Scheduled(cron = "0 45 08 * * MON-FRI")
    @Scheduled(cron = "0 30 09 * * MON-FRI")
// hodina 3.
    @Scheduled(cron = "0 40 09 * * MON-FRI")
    @Scheduled(cron = "0 25 10 * * MON-FRI")
// hodina 4.
    @Scheduled(cron = "0 40 10 * * MON-FRI")
    @Scheduled(cron = "0 25 11 * * MON-FRI")
// hodina 5.
    @Scheduled(cron = "0 30 11 * * MON-FRI")
    @Scheduled(cron = "0 15 12 * * MON-FRI")
// hodina 6.
    @Scheduled(cron = "0 20 12 * * MON-FRI")
    @Scheduled(cron = "0 05 13 * * MON-FRI")
// hodina 7.
    @Scheduled(cron = "0 35 13 * * MON-FRI")
    @Scheduled(cron = "0 20 14 * * MON-FRI")
// hodina 8.
    @Scheduled(cron = "0 25 14 * * MON-FRI")
    @Scheduled(cron = "0 10 15 * * MON-FRI")

    // DEBUG
    //@Scheduled(cron = "0 * * * * *") // every minute, when seconds are 0

    private void start_zvonenie_short() {
        if (Application.get_mode() == 1){
            // DEBUG
            //System.out.println("short");
            /*
            System.out.println(
                    ConsoleModificator.bright_green()
                            + "Time of execution:          "
                            + ConsoleModificator.dark_white()
                            + formatter.format(LocalDateTime.now())
                            + ConsoleModificator.none()
            );
            */
            Application.zvonenie(songs);
        }
    }

    @Scheduled(cron = "0 10 15 * * MON-FRI")
    private void put_to_sleep(){
        Application.esp_sleep(Application.get_time_sleep());
    }
    @Scheduled(cron = "0 0 6 * * SAT-SUN")
    private void put_to_long_sleep(){
        Application.esp_sleep(Application.get_time_sleep_long());
    }
    // DEBUG
    // @Scheduled(cron = "*/2 * * * * *")
    /*
    private void system_search(){
        Application.control_esp_in_system("here");
    }
    */
    // @Scheduled(cron = "*/2 * * * * *")
    /*
    private void print_system(){
        Application.print_esp_in_system("here");
    }
     */

}
