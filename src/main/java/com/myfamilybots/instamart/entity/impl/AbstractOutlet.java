package com.myfamilybots.instamart.entity.impl;

import com.myfamilybots.instamart.entity.Employee;
import com.myfamilybots.instamart.entity.Order;
import com.myfamilybots.instamart.entity.Outlet;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: AbstractOutlet.java
 * Date/time: 15 декабрь 2021 in 6:27
 */
@Getter
@Setter
public abstract class AbstractOutlet implements Outlet {
    protected String outletNumber;
    protected Super supervisor;
    protected Map<String, Employee> seniors = new ConcurrentHashMap<>();
    protected Map<String, Employee> pickers = new ConcurrentHashMap<>();
    protected Map<String, Employee> couriers = new ConcurrentHashMap<>();
    protected Map<String, Order> orders = new ConcurrentHashMap<>();
}
