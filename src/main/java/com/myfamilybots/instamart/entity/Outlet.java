package com.myfamilybots.instamart.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: Outlet.java
 * Date/time: 15 декабрь 2021 in 6:21
 */
@Component("TradePoint")
@Scope("prototype")
public interface Outlet {
    String getOutletNumber();

    void setOutletNumber(String outletNumber);
}
