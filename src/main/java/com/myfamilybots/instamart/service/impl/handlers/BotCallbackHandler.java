package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.EmployeeChats;
import com.myfamilybots.instamart.service.impl.AbstractCallbackHandler;
import com.myfamilybots.instamart.service.impl.services.BotTextService;
import com.myfamilybots.instamart.util.BotState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: BotCallbackHandler.java
 * Date/time: 15 декабрь 2021 in 22:35
 */
@Component
@Scope("prototype")
@Setter
@Getter
public class BotCallbackHandler extends AbstractCallbackHandler {
    private final ApplicationContext context;
    private Message inMessage;
    private CallbackQuery callbackQuery;
    private BotApiMethod<?> response;
    private EmployeeChats employeeChats;
    private BotTextService service;
    private BotState botState;
    private Employee employee;

    @Autowired
    public BotCallbackHandler(ApplicationContext context) {
        this.context = context;
        employeeChats = context.getBean(EmployeeChats.class);
        service = context.getBean(BotTextService.class);
    }

    @Override
    public void setInMessage(Message inMessage) {

    }

    @Override
    public void context() {
        inMessage = callbackQuery.getMessage();

    }

    @Override
    public BotApiMethod<?> getResponse() {
        return null;
    }
}
