package com.myfamilybots.instamart.service.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: ReplyMessagesService.java
 * Date/time: 18 декабрь 2021 in 7:59
 */
@Service
public class ReplyMessagesService {
    private final LocaleMessageService localeMessageService;

    @Autowired
    public ReplyMessagesService(LocaleMessageService messageService) {
        this.localeMessageService = messageService;
    }

    public SendMessage getReplyMessage(String chatId, String replyMessage) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(String chatId, String replyMessage, Object... args) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage, args));
    }

    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }

}
