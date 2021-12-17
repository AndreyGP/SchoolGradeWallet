package com.myfamilybots.instamart.service.impl.services;

import com.myfamilybots.instamart.service.impl.AbstractMessageService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: IncomingTextMessageService.java
 * Date/time: 14 декабрь 2021 in 6:12
 */

@Service("IncomingMessageService")
@Scope("prototype")
public class IncomingMessageService extends AbstractMessageService {

    @Override
    public SendMessage responseOnIncomingMessage(Message inMessage) {
        inMessage.getEntities();
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Разработчик спит\uD83D\uDE34, бот не работает \uD83E\uDD37\u200D♂️")
                .build();
    }

    @Override
    public SendMessage responseOnIncomingMessage(Message inMessage, String botToken) {
        this.setBotToken(botToken);
        return this.responseOnIncomingMessage(inMessage);
    }
}
