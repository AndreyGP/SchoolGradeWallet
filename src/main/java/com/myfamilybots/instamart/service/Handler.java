package com.myfamilybots.instamart.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: Handler.java
 * Date/time: 18 декабрь 2021 in 5:26
 */

public interface Handler {
    void setInMessage(Message inMessage);
    void context();
    BotApiMethod<?> getResponse();
}
