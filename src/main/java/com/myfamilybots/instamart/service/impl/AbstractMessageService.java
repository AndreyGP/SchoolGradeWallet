package com.myfamilybots.instamart.service.impl;

import com.myfamilybots.instamart.service.MessageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by Home Work Studio AndrHey [diver]
 * FileName: AbstractMessageService.java
 * Date/time: 15 декабрь 2021 in 0:11
 */
@Getter
@Setter
public abstract class AbstractMessageService implements MessageService {
    @Autowired
    private ApplicationContext context;
    private String botToken;
}
