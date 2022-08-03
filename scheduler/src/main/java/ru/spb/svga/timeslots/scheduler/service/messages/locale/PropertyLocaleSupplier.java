package ru.spb.svga.timeslots.scheduler.service.messages.locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.spb.svga.timeslots.scheduler.configuration.properties.MessageSourceConfiguration;

import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class PropertyLocaleSupplier implements Supplier<Locale> {
    @NonNull
    private final MessageSourceConfiguration.LocaleSetting localeSetting;

    private Locale locale;

    @Override
    public Locale get() {
        if (locale == null) {
            locale = findLocale();
            return locale;
        }
        return locale;
    }

    private Locale findLocale() {
        return Stream.of(Locale.getAvailableLocales())
                .filter(loc -> loc.getCountry().equalsIgnoreCase(localeSetting.country()))
                .filter(loc -> loc.getLanguage().equalsIgnoreCase(localeSetting.language()))
                .filter(loc -> loc.getVariant().equalsIgnoreCase(localeSetting.variant()))
                .filter(loc -> loc.getScript().equalsIgnoreCase(localeSetting.script()))
                .findFirst()
                .orElseGet(() -> Locale.forLanguageTag(localeSetting.fallback()));
    }
}
