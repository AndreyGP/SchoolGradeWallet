package com.myfamilybots.instamart.service.impl.services;

import com.myfamilybots.instamart.entity.Employee;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotTextService.java
 * Date/time: 18 декабрь 2021 in 5:09
 */
@Component
@Scope("prototype")
@Setter
public class BotTextService {
    private Message inMessage;
    private boolean devMode;

    public SendMessage getNotSuchCommand() {
        return null;
    }

    public SendMessage getFaq() {
        return null;
    }

    public BotApiMethod<?> getPhoneNumberAdded() {
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text("Выбирете пожалуйста вашу должность:")
                .build();
    }

    public SendMessage getHelloSupervisor(Employee supervisor) {
        String response = supervisor.getEmployeeFirstAndLastName() + ", Добро пожаловать в систему!\n"
                + "Ваш текущий чат (номер телефона) привязан как основной к системе"
                + "Давайте инициализируем и закрепим за Вами в системе первую точку.\n"
                + "Для этого введите и отправьте её код из метабазы:";
        return SendMessage.builder()
                .chatId(inMessage.getChatId().toString())
                .text(response)
                .build();
    }

    public String confirmText() {
        return "Подтверите Ваш выбор:\n";
    }
}
