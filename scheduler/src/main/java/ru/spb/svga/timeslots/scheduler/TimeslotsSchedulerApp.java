package ru.spb.svga.timeslots.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class TimeslotsSchedulerApp {

    private static final String APP_START = "timeslot.scheduler.starting";
    private static final String APP_STOP = "timeslot.scheduler.stopping";

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TimeslotsSchedulerApp.class);
    }
}
