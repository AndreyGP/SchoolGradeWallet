package com.myfamilybots.instamart.entity.impl;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.Outlet;
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
    private EmployeeRole employeeRole;
    private BotState botState;
    private boolean isTrainee;
    private String chatId;
    private String phoneNumber;
    private String username;
    private String fullName;
    private String firstName;
    private String lastName;
    private Outlet baseOutlet;
    private Outlet currentOutlet;


    @Override
    public String getEmployeeInfo() {
        return firstName + "\n"
                + employeeRole.getEmployeeRole() + "\n"
                + "Базовая точка: " + baseOutlet
                + "В смене: " + currentOutlet;
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
    public String getBaseOutlet() {
        return baseOutlet.getOutletNumber();
    }

    @Override
    public String getCurrentOutlet() {
        return currentOutlet.getOutletNumber();
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
