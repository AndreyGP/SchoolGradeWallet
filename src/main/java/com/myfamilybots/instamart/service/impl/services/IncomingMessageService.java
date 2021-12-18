package com.myfamilybots.instamart.service.impl.services;

import com.myfamilybots.instamart.service.Handler;
import com.myfamilybots.instamart.service.impl.AbstractMessageService;
import com.myfamilybots.instamart.service.impl.handlers.BotCommandHandler;
import com.myfamilybots.instamart.service.impl.handlers.BotMessageHandler;
import com.myfamilybots.instamart.service.impl.handlers.BotTextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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
    @Autowired
    private ApplicationContext context;
    private Message inMessage;
    private BotApiMethod<?> response;

    @Override
    public BotApiMethod<?> responseOnIncomingMessage(final Message inMessage) {
        this.inMessage = inMessage;
        if (inMessage.hasEntities() && inMessage.getEntities().get(0).getType().equals("bot_command")) {
//            botCommandHandling();
            BotCommandHandler handler = context.getBean(BotCommandHandler.class);
            handler.setInMessage(inMessage);
            handler.context();
            response = handler.getResponse();
        } else if (inMessage.hasText()) {
//            botTextHandling();
//            BotMessageHandler handler = context.getBean(BotMessageHandler.class);
//            handler.responseCallback(inMessage.);
//            handler.context();
//            response = handler.getResponse();
        }
        return response;
    }

    @Override
    public BotApiMethod<?> responseOnIncomingMessage(final Message inMessage, final String botToken) {
        this.setBotToken(botToken);
        return this.responseOnIncomingMessage(inMessage);
    }

    private void botCommandHandling() {
        handling(context.getBean(BotCommandHandler.class));
    }

    private void botTextHandling() {
        handling(context.getBean(BotTextHandler.class));
    }

    private void handling(Handler handler) {
        handler.setInMessage(inMessage);
        handler.context();
        response = handler.getResponse();
    }
}
