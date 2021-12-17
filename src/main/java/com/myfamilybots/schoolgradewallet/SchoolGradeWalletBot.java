package com.myfamilybots.schoolgradewallet;


import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: SchoolGradeWalletBot.java
 * Date/time: 12 декабрь 2021 in 2:47
 */
@Getter
@Setter
public class SchoolGradeWalletBot extends TelegramWebhookBot {
    private long alenaChatId = 1650000134;
    private long andreiChatId = 833886146;
    private Map<Long, String> family = new ConcurrentHashMap<>();
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private String fileUploadPath;

    public SchoolGradeWalletBot(DefaultBotOptions options) {
        super(options);
        family.put(andreiChatId, "Андрей");
        family.put(alenaChatId, "Алёна");
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        if (update.getMessage() != null && update.getMessage().hasDocument()) {
            String chatId = update.getMessage().getChatId().toString();
            Document document = update.getMessage().getDocument();
            try {
                uploadFile(document.getFileName(), document.getFileId());
                return SendMessage.builder()
                        .chatId(chatId)
                        .text("Upload")
                        .disableNotification(true)
                        .build();
            } catch (MalformedURLException e) {
                return SendMessage.builder()
                        .chatId(chatId)
                        .text(e.toString())
                        .build();
            }
        }

        if (update.getMessage() != null && update.getMessage().hasText()) {

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

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
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

    public void uploadFile(String fileName, String fileId) throws MalformedURLException {
        URL url = new URL("https://api.telegram.org/bot" + botToken + "/getFile?file_id=" + fileId);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
             FileOutputStream fos = new FileOutputStream("C:\\Users\\diver\\OneDrive\\Documents\\testbot\\" + fileName);) {

            String res = in.readLine();
            JSONObject jresult = new JSONObject(res);
            JSONObject path = jresult.getJSONObject("result");
            String filePath = path.getString("file_path");

            URL downoload = new URL("https://api.telegram.org/file/bot" + botToken + "/" + filePath);

            ReadableByteChannel rbc = Channels.newChannel(downoload.openStream());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (IOException ignore) {

        }

    }
}
