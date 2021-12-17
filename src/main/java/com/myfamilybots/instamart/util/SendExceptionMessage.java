package com.myfamilybots.instamart.util;

import lombok.Builder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: SendExceptionMessage.java
 * Date/time: 14 декабрь 2021 in 4:28
 */
@Builder
public class SendExceptionMessage {
    private String botToken;
    private String chatId;
    private String message;

    public void execute(String method) {
        if (method.equals("GET")) {
            sendGet();
        }
        if (method.equals("POST")) {
            sendPost();
        }
    }

    // HTTP-запрос GET
    private void sendGet() {

        String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                botToken, chatId, message);

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // HTTP-запрос POST
    private void sendPost() {

        String url = "https://api.telegram.org";
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            String urlParameters = String.format("bot=%s&chat_id=%s&text=%s",
                    botToken, chatId, message);

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(urlParameters);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
