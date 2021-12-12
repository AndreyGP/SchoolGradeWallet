package com.myfamilybots.schoolgradewallet.controller;

import com.myfamilybots.schoolgradewallet.SchoolGradeWalletBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: WebHookController.java
 * Date/time: 12 декабрь 2021 in 4:09
 */
@RestController
public class WebHookController {
    private final SchoolGradeWalletBot bot;

    @Autowired
    public WebHookController(SchoolGradeWalletBot bot) {
        this.bot = bot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
