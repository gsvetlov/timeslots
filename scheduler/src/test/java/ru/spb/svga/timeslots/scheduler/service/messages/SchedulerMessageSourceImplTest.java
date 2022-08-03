package ru.spb.svga.timeslots.scheduler.service.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.spb.svga.timeslots.scheduler.configuration.TimeslotsSchedulerConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        properties = {
                "message.source.location.bundle=scheduler_messages_test",
                "message.source.location.common=scheduler_messages_test_common.properties",
                "message.source.locale-setting.fallback=en-US",
                "message.source.locale-setting.language=ru",
                "message.source.locale-setting.country=ru"
        },
        classes = {
                SchedulerMessageSource.class,
                TimeslotsSchedulerConfiguration.class
        })

class SchedulerMessageSourceImplTest {
    private static final String BUNDLE_ONE = "test.bundle.one";
    private static final String BUNDLE_ONE_RU_CONTENT = "Это сообщение на русском";
    private static final String RUSSIAN_LOCALE = "ru-RU";
    private static final String UNKNOWN_CODE = "code.unknown";
    private static final String UNKNOWN_CODE_RESPONSE = "test message code.unknown not found in resource bundle";
    @Autowired
    SchedulerMessageSource messageSource;

    @Test
    void getMessage_inRussianLocale_works() {
        var message = messageSource.getMessage(BUNDLE_ONE);
        assertNotNull(message);
        assertEquals(BUNDLE_ONE_RU_CONTENT, message);
    }

    @Test
    void getMessage_invalidCode_returnValidResponse() {
        var message = messageSource.getMessage(UNKNOWN_CODE);
        assertEquals(UNKNOWN_CODE_RESPONSE, message);
    }

    @Test
    void getLocale_returnsCorrectLocale() {
        var localeTag = messageSource.getLocale().toLanguageTag();
        assertEquals(RUSSIAN_LOCALE, localeTag);
    }
}
