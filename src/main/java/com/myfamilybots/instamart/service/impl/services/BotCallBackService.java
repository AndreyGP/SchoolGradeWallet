package com.myfamilybots.instamart.service.impl.services;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: BotCallBackService.java
 * Date/time: 18 декабрь 2021 in 7:24
 */
@Component
@Scope("prototype")
@Setter
public class BotCallBackService {
    private Message inMessage;
    private boolean devMode;
}
