package ru.spb.svga.timeslots.scheduler.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@ConfigurationProperties(prefix = "message.source")
public record MessageSourceConfiguration(Location location, LocaleSetting localeSetting) {
    public MessageSourceConfiguration(Location location, LocaleSetting localeSetting) {
        this.location = Objects.requireNonNull(location);
        this.localeSetting = Objects.requireNonNullElseGet(localeSetting, getDefaultSettings());
    }

    public record Location(String bundle, String common) {
        public Location {
            Objects.requireNonNull(bundle);
            Objects.requireNonNull(common);
        }
    }

    public record LocaleSetting(String language, String country, String variant, String script, String fallback) {
        private static final String EMPTY = "";
        private static final String DEFAULT_FALLBACK = "en-US";

        public LocaleSetting(String language, String country, String variant, String script, String fallback) {
            this.language = requireNonNull(language);
            this.country = requireNonNull(country);
            this.variant = requireNonNullElse(variant, EMPTY);
            this.script = requireNonNullElse(script, EMPTY);
            this.fallback = requireNonNullElse(fallback, DEFAULT_FALLBACK);
        }
    }

    private Supplier<LocaleSetting> getDefaultSettings() {
        return () -> new LocaleSetting(Locale.getDefault().getLanguage(),
                                       Locale.getDefault().getCountry(),
                                       Locale.getDefault().getVariant(),
                                       Locale.getDefault().getVariant(),
                                       LocaleSetting.DEFAULT_FALLBACK);
    }
}
