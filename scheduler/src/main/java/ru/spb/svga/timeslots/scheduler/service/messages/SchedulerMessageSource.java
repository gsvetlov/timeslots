package ru.spb.svga.timeslots.scheduler.service.messages;

import org.springframework.context.MessageSource;

import java.util.Locale;

public interface SchedulerMessageSource extends MessageSource {

    String getMessage(String code);

    String getMessage(String code, Object... args);

    Locale getLocale();
}
