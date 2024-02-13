package com.example.apicombination;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasksShort {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    static String[] songs = {
            "ta_nase.mp3",
            "jozin.mp3"
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


    private void start_zvonenie() {
        if (ApiCombinationApplication.get_mode() == 1){
            System.out.println(
                        ConsoleModificator.bright_green()
                        + "Time of execution:          "
                        + ConsoleModificator.dark_white()
                        + formatter.format(LocalDateTime.now())
                        + ConsoleModificator.none()
            );
            ApiCombinationApplication.zvonenie(songs);
        }
    }
}
