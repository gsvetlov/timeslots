package ru.spb.svga.timeslots.scheduler.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import ru.spb.svga.timeslots.scheduler.configuration.properties.MessageSourceConfiguration;
import ru.spb.svga.timeslots.scheduler.service.messages.SchedulerMessageSourceImpl;
import ru.spb.svga.timeslots.scheduler.service.messages.locale.PropertyLocaleSupplier;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;
import java.util.function.Supplier;

@Configuration
@ConfigurationPropertiesScan("ru.spb.svga.timeslots.scheduler.configuration.properties")
@RequiredArgsConstructor
public class TimeslotsSchedulerConfiguration {

    private static final String UTF8_ENCODING = StandardCharsets.UTF_8.name();
    private final MessageSourceConfiguration sourceConfiguration;

    @Bean("schedulerMessageSource")
    public MessageSource getMessageSource(Supplier<Locale> localeSupplier) {
        var source = new ReloadableResourceBundleMessageSource();
        source.setBasename(sourceConfiguration.location().bundle());
        source.setDefaultEncoding(UTF8_ENCODING);
        source.setDefaultLocale(localeSupplier.get());
        source.setCommonMessages(getCommonMessages());
        return new SchedulerMessageSourceImpl(source, localeSupplier);
    }

    @SneakyThrows
    private Properties getCommonMessages() {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource(sourceConfiguration.location().common()));
        factoryBean.setFileEncoding(UTF8_ENCODING);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean("localeProvider")
    public Supplier<Locale> getLocaleSupplier() {
        return new PropertyLocaleSupplier(sourceConfiguration.localeSetting());
    }
}
