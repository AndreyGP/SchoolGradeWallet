package com.myfamilybots.instamart.entity;

import com.myfamilybots.instamart.entity.enums.EmployeeRole;
import com.myfamilybots.instamart.entity.impl.Developer;
import com.myfamilybots.instamart.entity.impl.NewEmployee;
import com.myfamilybots.instamart.util.BotState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: EmployeeChats.java
 * Date/time: 17 декабрь 2021 in 22:42
 */
@Component("EmployeeChat")
@Scope("singleton")
public class EmployeeChats {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    private final static Map<String, Employee> employeeMap = new ConcurrentHashMap<>();

    public void clear() {
        Developer developer;
        Optional<Employee> optionalEmployee = employeeMap.values().stream()
                .filter(employee -> employee.getEmployeeRole() == EmployeeRole.DEV)
                .findFirst();
        if (optionalEmployee.isPresent()) {
            developer = (Developer) optionalEmployee.get();
            String chatId = employeeMap.entrySet().stream()
                    .filter(e -> e.getValue().getEmployeeRole() == EmployeeRole.DEV)
                    .findFirst()
                    .get()
                    .getKey();
            employeeMap.clear();
            employeeMap.put(chatId, developer);
        }
    }

    public void clearAll() {
        employeeMap.clear();
    }

    public Employee getEmployeeByChatId(String chatId) {
        if (employeeMap.isEmpty()) {
            return addDev(chatId);
        }
        if (employeeMap.containsKey(chatId)) {
            return employeeMap.get(chatId);
        }

        try {
            readLock.lock();
            return addAndReturnNewEmployee(chatId);
        } finally {
            if (readLock.tryLock())
                readLock.unlock();
        }

    }

    public SendMessage getAllEmployee(String chatId) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Employee> employ : employeeMap.entrySet()) {
            buffer.append(employ.getKey()).append(" ").append(employ.getValue().getEmployeeRole()).append("\n");
        }
        if (buffer.toString().isEmpty()) {
            return SendMessage.builder()
                    .chatId(chatId)
                    .text("No body")
                    .build();
        }
        return SendMessage.builder()
                .chatId(chatId)
                .text(buffer.toString())
                .build();
    }

    private Employee addAndReturnNewEmployee(String chatId) {
        addNewEmployee(chatId);
        return employeeMap.get(chatId);
    }

    private Employee addDev(String chatId) {
        Employee developer = new Developer();
        developer.setEmployeeRole(EmployeeRole.DEV);
        developer.setChatId(chatId);
        developer.setBotState(BotState.DEV_MENU);
        employeeMap.put(chatId, developer);
        return developer;
    }

    private void addNewEmployee(String chatId) {
        Employee employee = new NewEmployee();
        employee.setEmployeeRole(EmployeeRole.NEW);
        employee.setChatId(chatId);
        employee.setBotState(BotState.START);
        employeeMap.put(chatId, employee);
    }

    public void remove(String chatId) {
        employeeMap.remove(chatId);
    }
}
