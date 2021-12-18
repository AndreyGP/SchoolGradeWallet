package com.myfamilybots.instamart.entity.impl;

import com.myfamilybots.instamart.entity.Outlet;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: Super.java
 * Date/time: 15 декабрь 2021 in 6:18
 */
@Component("Supervisor")
@Scope("prototype")
public class Super extends Senior {
    private final Map<String, Outlet> outletMap = new ConcurrentHashMap<>();

    public void addOutlet(Outlet outlet) {
        outletMap.put(outlet.getOutletNumber(), outlet);
    }

    public void removeOutlet(String outletNumber) {
        outletMap.remove(outletNumber);
    }
}
