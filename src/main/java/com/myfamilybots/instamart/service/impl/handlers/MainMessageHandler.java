package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.service.MessageService;
import com.myfamilybots.instamart.service.impl.AbstractMessageMessageHandler;
import com.myfamilybots.instamart.service.impl.services.IncomingDocumentService;
import com.myfamilybots.instamart.service.impl.services.IncomingMessageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: MainMessageHandler.java
 * Date/time: 15 декабрь 2021 in 7:39
 */
@Service("MessageHandler")
@Scope("prototype")
@Getter
public class MainMessageHandler extends AbstractMessageMessageHandler {
    @Autowired
    private ApplicationContext context;

    public BotApiMethod<Message> response(Message inMessage, String botToken) {
        if (inMessage.hasText()) {
            MessageService service = (IncomingMessageService) getContext().getBean("IncomingMessageService");
            return service.responseOnIncomingMessage(inMessage, botToken);
        }
        if (inMessage.hasDocument()) {
            MessageService service = (IncomingDocumentService) getContext().getBean("IncomingDocumentService");
            return service.responseOnIncomingMessage(inMessage, botToken);
        }
        return null;
    }
}
