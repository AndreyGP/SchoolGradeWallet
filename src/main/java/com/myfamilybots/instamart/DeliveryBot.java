package com.myfamilybots.instamart;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: DeliveryBot.java
 * Date/time: 13 декабрь 2021 in 3:30
 */

@Setter
@Getter
public class DeliveryBot extends TelegramWebhookBot {
    private long alenaChatId = 1650000134;
    private long andreiChatId = 833886146;
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private String fileUploadPath;

    public DeliveryBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            Message inMessage = update.getMessage();
            long chatId = inMessage.getChatId();
//            String message = inMessage.getText();
            String messageText;
//
            if (inMessage.getText().equals("/start"))
                return new SendMessage().setChatId(chatId).setText(getWelcomeMessage());

//            if (chatId == alenaChatId)
//                return new SendMessage().setChatId(andreiChatId).setText("Алёна: \n" + message);
//
//            if (chatId == andreiChatId)
//                return new SendMessage().setChatId(alenaChatId).setText("Андрей: \n" + message);


            if (inMessage.getText().equals("Ты ещё живой?")) {
                messageText = "Не дождёшься! :)";
            } else {
                messageText = inMessage.getChat().getFirstName() + ", Бот пока не работает ";
            }

            return new SendMessage()
                    .setChatId(chatId)
                    .setText(messageText).enableMarkdown(true);
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    private String getWelcomeMessage() {
        return "Success!";
    }

    private void uploadFile(String fileName, String fileId) throws MalformedURLException {
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
