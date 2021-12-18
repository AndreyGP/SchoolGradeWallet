package com.myfamilybots.instamart.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotCommand.java
 * Date/time: 17 декабрь 2021 in 22:01
 */

public class BotCommand {
    private static Map<String, BotState> botStateMap = new ConcurrentHashMap<>();

    static {
        botStateMap.put("/start", BotState.START);
        botStateMap.put("/init_outlet", BotState.INIT_OUTLET);
        botStateMap.put("/init_outlet_success", BotState.INIT_OUTLET_SUCCESS);
        botStateMap.put("/start_outlets", BotState.START_OUTLET);
        botStateMap.put("/add_super", BotState.ADD_SUPER);
        botStateMap.put("/add_senior", BotState.ADD_SENIOR);
        botStateMap.put("/hello_new_super", BotState.HELLO_NEW_SUPER);
        botStateMap.put("/faq", BotState.HELLO_FAQ);
        botStateMap.put("/interval", BotState.INTERVAL_FILTER);
        botStateMap.put("/status", BotState.STATUS_FILTER);
        botStateMap.put("/courier", BotState.COURIER_FILTER);
        botStateMap.put("/picker", BotState.PICKER_FILTER);
        botStateMap.put("/shift_init", BotState.SHIFT_INIT);
        botStateMap.put("/main_menu", BotState.MAIN_MENU_DEFAULT);
    }

    public static BotState getBotStateByBotCommand(String botCommand) {
        return botStateMap.getOrDefault(botCommand, BotState.UNCORRECT_COMMAND);
    }
}
