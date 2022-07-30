package ru.spb.svga.timeslots;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TimeslotsSchedulerApp {

    private static final String APP_START = "TimeslotsSchedulerApp starting";
    private static final String APP_STOP = "TimeslotsSchedulerApp stopping";

    public static void main(String[] args) {
        log.trace(APP_START);
        SpringApplication.run(TimeslotsSchedulerApp.class);
        log.warn(APP_STOP);
        log.trace(APP_STOP);
    }
}
