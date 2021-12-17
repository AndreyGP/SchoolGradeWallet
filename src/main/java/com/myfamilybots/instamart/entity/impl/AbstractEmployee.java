package com.myfamilybots.instamart.entity.impl;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.enums.EmployeeRole;
import com.myfamilybots.instamart.util.BotState;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: AbstractEmployee.java
 * Date/time: 15 декабрь 2021 in 5:51
 */
@Getter
@Setter
public abstract class AbstractEmployee implements Employee {
    protected EmployeeRole employeeRole;
    protected BotState botState = BotState.START;
    protected boolean isTrainee = true;
    protected String chatId;
    protected String username;
    protected String fullName;
    protected String firstName;
    protected String lastName;
    protected String baseTradePoint;
    protected String currentTradePoint;


    @Override
    public String getEmployeeInfo() {
        return firstName + "\n"
                + employeeRole.getEmployeeRole() + "\n"
                + "Базовая точка: " + baseTradePoint
                + "В смене: " + currentTradePoint;
    }

    @Override
    public String getEmployeeFirstName() {
        return firstName;
    }

    @Override
    public String getEmployeeLastName() {
        return lastName;
    }

    @Override
    public String getEmployeeFirstAndLastName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getEmployeeFullName() {
        return fullName;
    }

    @Override
    public String getBaseTradePoint() {
        return baseTradePoint;
    }

    @Override
    public String getCurrentTradePoint() {
        return currentTradePoint;
    }

    @Override
    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    @Override
    public boolean isTrainee() {
        return isTrainee;
    }

    @Override
    public String getChatId() {
        return chatId;
    }

    @Override
    public String getUserName() {
        return username;
    }
}
