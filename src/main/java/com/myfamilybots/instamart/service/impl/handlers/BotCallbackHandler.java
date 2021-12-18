package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.EmployeeChats;
import com.myfamilybots.instamart.entity.enums.EmployeeRole;
import com.myfamilybots.instamart.service.impl.AbstractCallbackHandler;
import com.myfamilybots.instamart.service.impl.services.BotCallBackService;
import com.myfamilybots.instamart.service.impl.services.BotTextService;
import com.myfamilybots.instamart.service.impl.services.MainMenuService;
import com.myfamilybots.instamart.service.impl.services.ReplyButtonService;
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
    private BotCallBackService service;
    private BotState botState;
    private Employee employee;

    @Autowired
    public BotCallbackHandler(ApplicationContext context) {
        this.context = context;
        employeeChats = context.getBean(EmployeeChats.class);
        service = context.getBean(BotCallBackService.class);
    }

    @Override
    public void setInMessage(Message inMessage) {
        this.inMessage = inMessage;
    }

    @Override
    public void context() {
        setInMessage(callbackQuery.getMessage());
        service.setInMessage(inMessage);
        service.setCallbackQuery(callbackQuery);

        String chatId = inMessage.getChatId().toString();
        employee = employeeChats.getEmployeeByChatId(chatId);

        botState = employee.getBotState();

        switch (botState) {
            case PHONE_REGISTRATION:
                setEmployeeRole();
                confirmEmployeeRole();
                break;
            case ROLE_REGISTRATION:
                confirm();
                switchingToMainMenu();
            default:
                break;
        }
    }

    @Override
    public BotApiMethod<?> getResponse() {
        return response;
    }

    private void setEmployeeRole() {
        String pushButton = callbackQuery.getData();
        switch (pushButton) {
            case "picker":
                employee.setEmployeeRole(EmployeeRole.PICKER);
                break;
            case "courier":
                employee.setEmployeeRole(EmployeeRole.COURIER);
                break;
            case "senior":
                employee.setEmployeeRole(EmployeeRole.SENIOR);
                break;
            case "trainee":
            default:
                employee.setEmployeeRole(EmployeeRole.TRAINEE);
                break;
        }
        employee.setBotState(BotState.ROLE_REGISTRATION);
    }


    private void confirmEmployeeRole() {
        response = service.getConfirmRole();
        ((SendMessage) response).enableMarkdown(true);
        ((SendMessage) response).setReplyMarkup(context.getBean(ReplyButtonService.class).getConfirmButtons());
    }

    private void confirm() {
        if (callbackQuery.getData().equals("yes")) {
            response = context.getBean(MainMenuService.class)
                    .getMainMenuMessage(inMessage.getChatId().toString(), "Воспользуйтесь главным меню:");
        } else {
            employeeChats.remove(inMessage.getChatId().toString());
            employee = null;
            response = context.getBean(BotCallBackService.class).sendAnswerCallbackQuery(
                    "Попробуйте заново, когда определитесь", false, callbackQuery);
        }
    }

    private void switchingToMainMenu() {
        employee.setBotState(BotState.MAIN_MENU_DEFAULT);
    }
}
