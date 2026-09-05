/**
 * 
 */
package com.duke.mfa.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author Kazi
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MfaApp {

    public static void main(String[] args) {
        SpringApplication.run(MfaApp.class, args);
    }
}
