package com.myfamilybots.instamart.service.impl.services;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotCommandService.java
 * Date/time: 18 декабрь 2021 in 3:04
 */
@Component
@Scope("prototype")
@Setter
public class BotCommandService {
    private Message inMessage;
    private boolean devMode;
    private List<String> fuckStarting = Arrays.asList("Вы уже нажимали старт. Отправьте /faq для справки",
            "Вы уже в системе. Отправьте /faq для справки",
            "Хорош отправлять это. Отправьте /faq для справки",
            "Смотрю понравилось? Отправьте /faq для справки",
            "Да бесполезно уже. Отправьте /faq для справки",
            "Для особо одарённых: Отправьте /faq для справки",
            "Вы уже нажимали старт. Отправьте /faq для справки",
            "Вы уже в системе. Отправьте /faq для справки",
            "Хорош отправлять это. Отправьте /faq для справки",
            "Смотрю понравилось? Отправьте /faq для справки",
            "Да бесполезно уже. Отправьте /faq для справки",
            "Для особо одарённых: Отправьте /faq для справки");

    public SendMessage getHelloNewUser() {
        if (devMode) {
            return SendMessage.builder()
                    .chatId(inMessage.getChatId().toString())
                    .text("Суперпользователь зарегестирован")
                    .build();
        }
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Добро пожаловать в Delivery System Bot!\nЧтобы начать, отправьте Ваш номер телефона, указанный в Shopper в формате\n+79998887766\n")
                .build();
    }

    public SendMessage getFaq() {
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Тут будет описание основных возможностей и команд")
                .build();
    }

    public SendMessage getNotSuchCommand() {
        return getMainMenu();
    }

    public SendMessage getMainMenu() {
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Режим главного меню")
                .build();
    }

    public SendMessage getFuckStart() {
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text(fuckStarting.get((int) (Math.random() * 10)))
                .build();
    }

}
