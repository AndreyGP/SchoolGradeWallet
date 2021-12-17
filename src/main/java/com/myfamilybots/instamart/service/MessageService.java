package com.myfamilybots.instamart.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: MessageService.java
 * Date/time: 14 декабрь 2021 in 23:41
 */

public interface MessageService {

    BotApiMethod<Message> responseOnIncomingMessage(Message inMessage);
    BotApiMethod<Message> responseOnIncomingMessage(Message inMessage, String botToken);

}
