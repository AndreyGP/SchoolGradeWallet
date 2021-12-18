package com.myfamilybots.instamart.service.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: LocaleMessageService.java
 * Date/time: 18 декабрь 2021 in 7:55
 */
@Service
public class LocaleMessageService {
    private final Locale locale;
    private final MessageSource messageSource;

    @Autowired
    public LocaleMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag("ru_RU");
    }


    public String getMessage(String message) {
        return messageSource.getMessage(message, null, locale);
    }

    public String getMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, locale);
    }
}
