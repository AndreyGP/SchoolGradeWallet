package com.myfamilybots.instamart.service.impl.services;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotCallBackService.java
 * Date/time: 18 декабрь 2021 in 7:24
 */
@Component
@Scope("prototype")
@Setter
public class BotCallBackService {
    private Message inMessage;
    private CallbackQuery callbackQuery;
    private boolean devMode;

    public BotApiMethod<?>  getConfirmRole() {
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Подтвердите должность:")
                .build();
    }

    private String getFinalRegistration() {
        return "Поздравляем!\nМинимальные настройки завершены.\nОсталось только скинуть по команде старшего"
                + " номер заказа, чтобы завершить все остальные настройки\n и получить полный доступ к функционалу системы";
    }

    public AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}
