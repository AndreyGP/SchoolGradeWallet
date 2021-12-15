package com.myfamilybots.instamart.entity;

import com.myfamilybots.instamart.entity.enums.EmployeeRole;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: Employee.java
 * Date/time: 15 декабрь 2021 in 4:44
 */

public interface Employee {
    String getChatId();
    String getUserName();
    String getEmployeeInfo();
    String getEmployeeFirstName();
    String getEmployeeLastName();
    String getEmployeeFirstAndLastName();
    String getEmployeeFullName();
    String getBaseTradePoint();
    String getCurrentTradePoint();
    EmployeeRole getEmployeeRole();

}
