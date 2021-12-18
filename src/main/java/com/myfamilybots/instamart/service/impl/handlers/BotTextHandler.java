package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.EmployeeChats;
import com.myfamilybots.instamart.entity.Outlets;
import com.myfamilybots.instamart.entity.enums.EmployeeRole;
import com.myfamilybots.instamart.entity.impl.NewEmployee;
import com.myfamilybots.instamart.entity.impl.Super;
import com.myfamilybots.instamart.service.Handler;
import com.myfamilybots.instamart.service.impl.services.BotCommandService;
import com.myfamilybots.instamart.service.impl.services.BotTextService;
import com.myfamilybots.instamart.service.impl.services.ReplyButtonService;
import com.myfamilybots.instamart.util.BotCommand;
import com.myfamilybots.instamart.util.BotState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotTextHandler.java
 * Date/time: 18 декабрь 2021 in 5:07
 */
@Component
@Scope("prototype")
@Setter
@Getter
public class BotTextHandler implements Handler {
    private final ApplicationContext context;
    private Message inMessage;
    private BotApiMethod<?> response;
    private EmployeeChats employeeChats;
    private BotTextService service;
    private BotState botState;
    private Employee employee;

    @Autowired
    public BotTextHandler(ApplicationContext context) {
        this.context = context;
        employeeChats = context.getBean(EmployeeChats.class);
        service = context.getBean(BotTextService.class);
    }

    public void context() {
        service.setInMessage(inMessage);
        employee = employeeChats.getEmployeeByChatId(inMessage.getChatId().toString());
        botState = employee.getBotState();

        switch (botState) {
            case START:
                startContext();
                break;
            case ROLE_REGISTRATION:
            case HELLO_FAQ:
                response = service.getFaq();
                break;
            case MAIN_MENU_DEFAULT:
            case ADD_SUPER:
            case ADD_SENIOR:
            case SHIFT_INIT:
            case INIT_OUTLET:
            case START_OUTLET:
            case PICKER_FILTER:
            case STATUS_FILTER:
            case COURIER_FILTER:
            case HELLO_NEW_SUPER:
            case INTERVAL_FILTER:
            case INIT_OUTLET_SUCCESS:
            case YOU_NOT_FOUND:
            case UNCORRECT_COMMAND:
            default:
                response = service.getNotSuchCommand();
                break;
        }
    }

    private void startContext() {
        NewEmployee newEmployee = (NewEmployee) employee;
        if (newEmployee.getPhoneNumber() == null && inMessage.getEntities().get(0).getType().equals("phone_number")) {
            if (context.getBean(Outlets.class).containsSupervisor(inMessage.getText())) {
                Employee supervisor = context.getBean(Outlets.class).getSupervisorByPhoneNumber(inMessage.getText());
                supervisor.setChatId(inMessage.getChatId().toString());
                response = service.getHelloSupervisor(supervisor);
            }
            addPhoneNumber(inMessage.getText());
        }
    }

    private void addPhoneNumber(String phoneNumber) {
        NewEmployee newEmployee = (NewEmployee) employee;
        if (phoneNumber.matches("^(\\+)(\\d{11})$")) {
            newEmployee.setPhoneNumber(phoneNumber);
            response = service.getPhoneNumberAdded();
            ((SendMessage) response).enableMarkdown(true);
            ((SendMessage) response).setReplyMarkup(context.getBean(ReplyButtonService.class).getRoleQuestionButtons());
            employee.setBotState(BotState.ROLE_REGISTRATION);
        }
    }
}
