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
            String messageText = "";

            if (inMessage.getText().equals("/start"))
                return new SendMessage().setChatId(chatId).setText(getWelcomeMessage());

            if (inMessage.getText().equals("Ты ещё живой?")) {
                messageText = "Не дождёшься! :)";
            } else {
                messageText = inMessage.getChat().getFirstName() + ", echo bot returned -> " + inMessage.getText();
            }

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

    private String getWelcomeMessage() {
        return "Приветствую мой Друг!\n"
                + "Вскоре тут ты сможешь копить и обменивать свои оценки на разные классные штуки. Например, на время "
                + "игры в Fortnite и даже, если накопишь достаточно оценок, на крутые скины для своего бойца. И это "
                + "ещё не всё, но об этом позже \u23F3.\n"
                + "Итак, наша виртуальная валюта, на которую ты будешь обменивать свои пятёрки, называется \"Талант\". "
                + "Обменный курс лёгко запомнить, каждая оценка по любому предмету ровна:\n\n"
                + "\u26A1 5 = 10 талантов\n"
                + "\u2705 4 = 4 таланта\n"
                + "\u2B55 3 = 0 талантов\n"
                + "\u26D4 2 = -50 талантов\n"
                + "\u274C 1 обнуляет все накопленные таланты\n\n"
                + "Что сколько стоит, будет рассказано отдельной рассылкой, а пока готовься - запуск уже скоро, в новом году!\n"
                + "Учись хорошо мой Друг и всё у тебя будет!";
    }
}
