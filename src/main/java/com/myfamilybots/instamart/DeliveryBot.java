package com.myfamilybots.instamart;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: DeliveryBot.java
 * Date/time: 13 декабрь 2021 in 3:30
 */

@Setter
@Getter

public class DeliveryBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public DeliveryBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}
