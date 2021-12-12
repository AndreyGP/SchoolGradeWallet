package com.myfamilybots.schoolgradewallet;


import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: SchoolGradeWalletBot.java
 * Date/time: 12 декабрь 2021 in 2:47
 */
@Getter
@Setter
public class SchoolGradeWalletBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public SchoolGradeWalletBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            Message inMessage = update.getMessage();
            long chatId = inMessage.getChatId();
            String messageText = inMessage.getChat().getFirstName() + ", echo bot returned -> " + inMessage.getText();

            return new SendMessage()
                    .setChatId(chatId)
                    .setText(messageText);
        }
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


    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
