package com.myfamilybots.schoolgradewallet.appconfig;

import com.myfamilybots.schoolgradewallet.SchoolGradeWalletBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: BotConfiguration.java
 * Date/time: 12 декабрь 2021 in 3:17
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "tgbot")
public class BotConfiguration {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;
    private String fileUploadPath;

    @Bean
    public SchoolGradeWalletBot schoolGradeWalletBot() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        SchoolGradeWalletBot bot = new SchoolGradeWalletBot(options);
        bot.setWebHookPath(webHookPath);
        bot.setBotUserName(botUserName);
        bot.setBotToken(botToken);
        bot.setFileUploadPath(fileUploadPath);

        return bot;
    }
}
