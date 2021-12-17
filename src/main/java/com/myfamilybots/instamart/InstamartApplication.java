package com.myfamilybots.instamart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * SchoolGradeWallet Created by Home Work Studio AndrHey [diver]
 * FileName: InstamartApplication.java
 * Date/time: 13 декабрь 2021 in 3:24
 */
@RestController
@EnableScheduling
@SpringBootApplication
@EnableWebMvc
public class InstamartApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstamartApplication.class, args);
    }
}
