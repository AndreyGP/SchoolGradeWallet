package com.myfamilybots.instamart.controller;


import com.myfamilybots.instamart.DeliveryBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: WebHookController.java
 * Date/time: 13 декабрь 2021 in 3:23
 */
@RestController
public class WebHookController {
    private final DeliveryBot bot;

    @Autowired
    public WebHookController(DeliveryBot bot) {
        this.bot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
