package com.myfamilybots.schoolgradewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Bot wallet for school grades. Accumulation of valuations as currency with exchange for tangible and intangible values.
 * Score 5 = 10 talents
 * Score 4 = 4 talents
 * Score 3 = 0 talents
 * Score 2 = -50 talents
 * Score 1 = wallet zeroing
 */
@SpringBootApplication
public class SchoolGradeWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolGradeWalletApplication.class, args);
    }

}
