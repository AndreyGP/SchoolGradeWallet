package com.myfamilybots.instamart.appconfig;

import com.myfamilybots.instamart.bots.LongPollingDeliveryBot;
import com.myfamilybots.instamart.bots.WebHookDeliveryBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.DefaultBotOptions;

/**
 * DeliverySystem Created by Home Work Studio AndrHey [diver]
 * FileName: DeliveryBotConfig.java
 * Date/time: 13 декабрь 2021 in 3:22
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "instamart")
public class DeliveryBotConfig {
    private String webHookPath;
    private String longPollingPath;
    private String botUserName;
    private String botToken;
    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean(name = "WebHookDeliveryBot")
    public WebHookDeliveryBot webHookDeliveryBot() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        WebHookDeliveryBot bot = new WebHookDeliveryBot(options);
        bot.setWebHookPath(webHookPath);
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);

        return bot;
    }
//    @Bean(name = "LongPollingDeliveryBot")
//    public LongPollingDeliveryBot longPollingDeliveryBot(){
//        LongPollingDeliveryBot bot = new LongPollingDeliveryBot();
//        bot.setLongPollingPath(longPollingPath);
//        bot.setBotUserName(botUserName);
//        bot.setBotToken(botToken);
//        return bot;
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
