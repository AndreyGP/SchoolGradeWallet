package com.myfamilybots.instamart.service.impl.handlers;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.EmployeeChats;
import com.myfamilybots.instamart.entity.enums.EmployeeRole;
import com.myfamilybots.instamart.entity.impl.NewEmployee;
import com.myfamilybots.instamart.service.Handler;
import com.myfamilybots.instamart.service.impl.services.BotCommandService;
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
 * FileName: BotCommandHandler.java
 * Date/time: 18 декабрь 2021 in 2:36
 */
@Component
@Scope("prototype")
@Setter
@Getter
public class BotCommandHandler implements Handler {
    private final ApplicationContext context;
    private Message inMessage;
    private BotApiMethod<?> response;
    private EmployeeChats employeeChats;
    private BotCommandService service;
    private BotState botState;
    private Employee employee;

    @Autowired
    public BotCommandHandler(ApplicationContext context) {
        this.context = context;
        employeeChats = context.getBean(EmployeeChats.class);
        service = context.getBean(BotCommandService.class);
    }

    public void context() {
        service.setInMessage(inMessage);
        botState = BotCommand.getBotStateByBotCommand(inMessage.getEntities().get(0).getText());
        employee = employeeChats.getEmployeeByChatId(inMessage.getChatId().toString());

//        if (employee == null) return;

        if (employee.getEmployeeRole() == EmployeeRole.DEV) {
            devMode();
        } else {
            switch (botState) {
                case START:
                    startContext();
                    break;
                case ROLE_REGISTRATION:
                case HELLO_FAQ:
                    response = service.getFaq();
                    break;
                case DEV_MENU:
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
    }

    private void startContext() {
        if (employee.getBotState() == BotState.HELLO_FAQ) {
            employee.setBotState(BotState.MAIN_MENU_DEFAULT);
            response = service.getMainMenu();
        } else if (employee.getBotState() == BotState.START && ((NewEmployee) employee).getPhoneNumber() == null) {
            response = service.getHelloNewUser();
        } else {
            response = service.getFuckStart();
        }
    }

    private void devMode() {
        if (inMessage.getEntities().get(0).getText().equals("/clear"))
            employeeChats.clear();

        if (inMessage.getEntities().get(0).getText().equals("/clear_all"))
            employeeChats.clearAll();

        if (botState == BotState.START) {
            if (employee.getBotState() == BotState.DEV_MENU) {
                employee.setBotState(BotState.MAIN_MENU_DEFAULT);
                service.setDevMode(true);
                response = service.getHelloNewUser();
                service.setDevMode(false);
            } else {
                response = employeeChats.getAllEmployee(inMessage.getChatId().toString());
            }
        }
    }
}
