package ru.spb.svga.timeslots.service.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.lang.NonNull;

import java.util.Locale;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class SchedulerMessageSourceImpl implements SchedulerMessageSource {
    private static final String MESSAGE_NOT_FOUND = "common.message.not.found";
    private final MessageSource messageSource;
    private final Supplier<Locale> localeSupplier;

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }

    @Override
    public String getMessage(String code) {
        var resolvable = new DefaultMessageSourceResolvable(new String[] {code}, getDefault(code));
        return messageSource.getMessage(resolvable, localeSupplier.get());
    }

    @Override
    public String getMessage(String code, Object... args) {
        var resolvable = new DefaultMessageSourceResolvable(new String[] {code}, args, getDefault(code));
        return messageSource.getMessage(resolvable, localeSupplier.get());
    }

    @Override
    public Locale getLocale() {
        return localeSupplier.get();
    }

    private String getDefault(@NonNull String... code) {
        return messageSource.getMessage(MESSAGE_NOT_FOUND, code, localeSupplier.get());
    }
}
