package com.myfamilybots.instamart.util;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * JSONFileParser Created by Home Work Studio AndrHey [diver]
 * FileName: JSONFileParser.java
 * Date/time: 13 декабрь 2021 in 21:58
 */
@Setter
@Getter
@Component
@Scope("prototype")
public class JSONFileParser {
    private String botToken;
    private String chatId;
    private String fileId;
    private String fileName;
    private String filePath;

    private List<String> getJSONStringsList() throws MalformedURLException {
        URL url = new URL("https://api.telegram.org/bot" + botToken + "/getFile?file_id=" + fileId);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String res = in.readLine();
            JSONObject jresult = new JSONObject(res);
            JSONObject path = jresult.getJSONObject("result");
            filePath = path.getString("file_path");
        } catch (IOException e) {
            new SendExceptionMessage.SendExceptionMessageBuilder()
                    .botToken(botToken)
                    .chatId(chatId)
                    .message("Ошибка+отправки+файла+" + fileName
                            + "+.+Попробуйте+ещё+раз+или+пора+сообщить+разработчику")
                    .build()
                    .execute("GET");
        }

        URL downoload = new URL("https://api.telegram.org/file/bot" + botToken + "/" + filePath);
        List<String> result = Collections.emptyList();
        try (BufferedReader outToString = new BufferedReader(new InputStreamReader(downoload.openStream()))) {
            StringBuilder strings = new StringBuilder();
            while (outToString.ready())
                strings.append(outToString.readLine());
            result = splitJSONsStringToJSONList(strings.toString());
        } catch (IOException e) {
            new SendExceptionMessage.SendExceptionMessageBuilder()
                    .botToken(botToken)
                    .chatId(chatId)
                    .message("Ошибка+загрузки+на+сервер+файла+" + fileName
                            + "+.+Попробуйте+ещё+раз+или+пора+сообщить+разработчику")
                    .build()
                    .execute("GET");
        }

        return result;
    }

    private List<String> splitJSONsStringToJSONList(final String jsons) {
        return Arrays.stream(jsons.substring(2, jsons.length() - 2).split("},\\{")).collect(Collectors.toList());
    }

    private List<List<String>> getOrdersList(final List<String> jsonStrings) {
        if (jsonStrings == null) return Collections.emptyList();
        return jsonStrings.parallelStream()
                .map(s -> Arrays.stream(s.replace("\":", "\" : ")
                        .replace(":null", ": null")
                        .replace("\\s:\\d", " : ")
                        .replace(" : ", " | ")
                        .replace(",\"", ";")
                        .replace("\"", "")
                        .split(";"))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private List<Map<String, String>> getListOrdersMap(final List<List<String>> ordersList) {
        List<Map<String, String>> result = new ArrayList<>();
        if (ordersList.isEmpty()) return result;
        for (List<String> orders : ordersList) {
            Map<String, String> orderMap = new HashMap<>();
            for (String fieldOrder : orders) {
                String[] keyValue = fieldOrder.split(" \\| ");
                if (keyValue.length > 1) orderMap.put(keyValue[0], keyValue[1]);
            }
            result.add(orderMap);
        }
        return result;
    }

    public List<Map<String, String>> getCurrentOrdersMap() {
        try {
            return getListOrdersMap(getOrdersList(getJSONStringsList()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
