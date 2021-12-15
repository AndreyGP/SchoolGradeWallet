package com.myfamilybots.instamart.entity.enums;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: OrderStatus.java
 * Date/time: 15 декабрь 2021 in 6:41
 */

public enum OrderStatus {
    SUSPENDED("Прогноз", "suspended"),
    CANSELED("Отменён", "canceled"),
    PAUSED("Приостановлен", "paused"),
    CAPTUDED("Подвешен", "doubled"),
    READY("Не взят", "ready"),
    COLLECTING("В сборке", "collecting"),
    ON_APPROVAL("Согласование", "on_approval"),
    ON_CASH_DESK("На кассе", "on_cash_desk"),
    PACKAGING("Пакуется", "packaging"),
    READY_TO_SHIP("На базе", "ready_to_ship"),
    SHIPPING("Доставка", "shipping"),
    SHIPPED("Доставлен", "shipped");

    private String statusRu;
    private String statusOriginal;

    OrderStatus(String statusRu, String statusOriginal) {
        this.statusRu = statusRu;
        this.statusOriginal = statusOriginal;
    }

    public String getStatusRu() {
        return statusRu;
    }

    public String getStatusOriginal() {
        return statusOriginal;
    }
}
