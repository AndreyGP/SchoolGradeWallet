package com.myfamilybots.instamart.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: Outlets.java
 * Date/time: 15 декабрь 2021 in 7:16
 */
@Component
public class Outlets {
    @Autowired
    private ApplicationContext context;
    private Map<String, Outlet> outlets = new ConcurrentHashMap<>();
}
