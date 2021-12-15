package com.myfamilybots.instamart.entity.impl;

import com.myfamilybots.instamart.entity.Order;
import com.myfamilybots.instamart.entity.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: AbstractOrder.java
 * Date/time: 15 декабрь 2021 in 6:38
 */
@Getter
@Setter
public abstract class AbstractOrder implements Order {
    private OrderStatus currentStatus;
    private String deliveryNumber;
    private String address;
    private String deliveryInterval;
    private String orderPickerName;
    private String orderCourierName;
    private String pickUpTime;
    private String endPickingTime;
    private String startDeliveryTime;
    private String endDeliveryTime;
    private String comment;
    private int grade;
    private int quantityOfPositions;
    private boolean delayedDelivery;
    private boolean courierAppointed;

}
