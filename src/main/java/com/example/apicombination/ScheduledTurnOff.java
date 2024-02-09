package com.example.apicombination;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTurnOff {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Scheduled(cron = "0 10 15 * * MON-FRI")
    private void turnOff() {
        int time = 900;
        System.out.println(
                        ConsoleModificator.bright_green()
                        + "Time of turn off:          "
                        + ConsoleModificator.dark_white()
                        + formatter.format(LocalDateTime.now())
                        + ConsoleModificator.none()
        );
        ApiCombinationApplication.sleep(time);
    }
}
