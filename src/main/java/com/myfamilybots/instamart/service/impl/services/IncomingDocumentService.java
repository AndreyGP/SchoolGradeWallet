package com.myfamilybots.instamart.service.impl.services;

import com.myfamilybots.instamart.service.impl.AbstractMessageService;
import com.myfamilybots.instamart.util.JSONFileParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.stream.Collectors;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: IncomingDocumentService.java
 * Date/time: 15 декабрь 2021 in 0:05
 */
@Service("IncomingDocumentService")
@Scope("prototype")
public class IncomingDocumentService extends AbstractMessageService {

    @Override
    public BotApiMethod<Message> responseOnIncomingMessage(Message inMessage) {
        String chatId = inMessage.getChatId().toString();
        Document incomingFile = inMessage.getDocument();

        if (incomingFile.getMimeType().equals("application/json")) {
            JSONFileParser jsonFileParser = getContext().getBean(JSONFileParser.class);
            jsonFileParser.setBotToken(getBotToken());
            jsonFileParser.setChatId(chatId);
            jsonFileParser.setFileId(incomingFile.getFileId());
            jsonFileParser.setFileName(incomingFile.getFileName());

            String message = jsonFileParser.getCurrentOrdersMap().stream()
                    .flatMap(map -> map.keySet().stream()
                            .map(s -> (s + "\n")))
                    .limit(50)
                    .collect(Collectors.joining());

            return SendMessage.builder()
                    .chatId(chatId)
                    .text(message)
                    .build();
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text(inMessage.getFrom().getFirstName() + "! Ожидается файл .json формата")
                .build();
    }

    @Override
    public BotApiMethod<Message> responseOnIncomingMessage(Message inMessage, String botToken) {
        this.setBotToken(botToken);
        return responseOnIncomingMessage(inMessage);
    }
}
