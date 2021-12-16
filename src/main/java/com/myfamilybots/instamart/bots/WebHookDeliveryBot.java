package com.myfamilybots.instamart.bots;

import com.myfamilybots.instamart.service.MessageHandler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: WebHookDeliveryBot.java
 * Date/time: 13 декабрь 2021 in 3:30
 */

@Setter
@Getter
public class WebHookDeliveryBot extends TelegramWebhookBot {
    @Autowired
    private ApplicationContext context;
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public WebHookDeliveryBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() == null)
            return null;
        return ((MessageHandler) context.getBean("MessageHandler"))
                .response(update.getMessage(), botToken);
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
