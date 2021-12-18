package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.service.MessageService;
import com.myfamilybots.instamart.service.impl.AbstractMessageHandler;
import com.myfamilybots.instamart.service.impl.services.IncomingDocumentService;
import com.myfamilybots.instamart.service.impl.services.IncomingMessageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: BotMessageHandler.java
 * Date/time: 15 декабрь 2021 in 7:39
 */
@Service("MessageHandler")
@Scope("prototype")
@Getter
public class BotMessageHandler extends AbstractMessageHandler {
    private final ApplicationContext context;

    @Autowired
    public BotMessageHandler(ApplicationContext context) {
        this.context = context;
    }

    public BotApiMethod<?> response(Message inMessage, String botToken) {
        if (inMessage.hasText()) {
            MessageService service = (IncomingMessageService) context.getBean("IncomingMessageService");
            return service.responseOnIncomingMessage(inMessage, botToken);
        }
        if (inMessage.hasDocument()) {
            MessageService service = (IncomingDocumentService) context.getBean("IncomingDocumentService");
            return service.responseOnIncomingMessage(inMessage, botToken);
        }
        return null;
    }

    public BotApiMethod<?> responseCallback(CallbackQuery callbackQuery) {
        BotCallbackHandler service = context.getBean(BotCallbackHandler.class);
        service.setCallbackQuery(callbackQuery);
        service.context();
        return service.getResponse();
    }
}
