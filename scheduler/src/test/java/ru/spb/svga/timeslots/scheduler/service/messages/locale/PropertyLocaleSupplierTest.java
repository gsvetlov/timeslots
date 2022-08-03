package ru.spb.svga.timeslots.scheduler.service.messages.locale;

import org.junit.jupiter.api.Test;
import ru.spb.svga.timeslots.scheduler.configuration.properties.MessageSourceConfiguration;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyLocaleSupplierTest {
    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_RU = "ru";
    private static final String COUNTRY_US = "us";
    private static final String COUNTRY_RU = "ru";
    private static final String TAG_EN_US = "en-US";
    private static final String TAG_RU_RU = "ru-RU";
    private static final String INVALID_CODE = "404";

    PropertyLocaleSupplier localeSupplier;

    @Test
    void get_returnsCorrectLocale() {
        var settings = getSetting(LANGUAGE_RU, COUNTRY_RU, TAG_EN_US);
        localeSupplier = new PropertyLocaleSupplier(settings);

        var locale = localeSupplier.get();
        assertEquals(Locale.forLanguageTag(TAG_RU_RU), locale);
    }

    @Test
    void get_withInvalidLanguage_returnFallback() {
        var settings = getSetting(INVALID_CODE, COUNTRY_US, TAG_RU_RU);
        localeSupplier = new PropertyLocaleSupplier(settings);

        var locale = localeSupplier.get();
        assertEquals(Locale.forLanguageTag(TAG_RU_RU), locale);
    }

    @Test
    void get_withInvalidCountry_returnFallback() {
        var settings = getSetting(LANGUAGE_EN, INVALID_CODE, TAG_RU_RU);
        localeSupplier = new PropertyLocaleSupplier(settings);

        var locale = localeSupplier.get();
        assertEquals(Locale.forLanguageTag(TAG_RU_RU), locale);
    }

    @Test
    void get_withNullFallback_defaultsToLocaleEN_US() {
        var settings = getSetting(INVALID_CODE, INVALID_CODE, null);
        localeSupplier = new PropertyLocaleSupplier(settings);

        var locale = localeSupplier.get();
        assertEquals(Locale.forLanguageTag(TAG_EN_US), locale);
    }

    private MessageSourceConfiguration.LocaleSetting getSetting(String language,
                                                                String country,
                                                                String fallback) {
        return new MessageSourceConfiguration.LocaleSetting(language, country, null, null, fallback);
    }
}
