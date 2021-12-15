package com.myfamilybots.instamart.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: null.java
 * Date/time: 15 декабрь 2021 in 7:30
 */

public interface MessageHandler {
    BotApiMethod<Message> response(Message inMessage, String botToken);
}
