package com.myfamilybots.instamart.appconfig;

import com.myfamilybots.instamart.DeliveryBot;
import com.myfamilybots.schoolgradewallet.SchoolGradeWalletBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: DeliveryBotConfig.java
 * Date/time: 13 декабрь 2021 in 3:22
 */
@Getter
@Setter
@Configuration
//@ComponentScan("com.myfamilybots.schoolgradewallet")
@ConfigurationProperties(prefix = "instamart")
public class DeliveryBotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;
    private String fileUploadPath;

    @Bean
    public DeliveryBot deliveryBot() {
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        DeliveryBot bot = new DeliveryBot(options);
        bot.setWebHookPath(webHookPath);
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setFileUploadPath(fileUploadPath);

        return bot;
    }
}
