package com.myfamilybots.instamart.bots;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: LongPollingDeliveryBot.java
 * Date/time: 15 декабрь 2021 in 2:58
 */
@Getter
@Setter
public class LongPollingDeliveryBot extends TelegramLongPollingBot {
//    @Autowired
    private ApplicationContext context;
    private String longPollingPath;
    private String botUserName;
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
