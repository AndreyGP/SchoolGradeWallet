package com.myfamilybots.instamart.entity.enums;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: EmployeeRole.java
 * Date/time: 15 декабрь 2021 in 4:50
 */

public enum EmployeeRole {
    NEW("Новый пользователь"),
    TRAINEE("Стажёр"),
    PICKER("Сборщик"),
    COURIER("Курьер"),
    SENIOR("Старший"),
    SUPER("Супервайзер"),
    DEV("Разработчик");

    private final String employeeRole;

    EmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }
}
