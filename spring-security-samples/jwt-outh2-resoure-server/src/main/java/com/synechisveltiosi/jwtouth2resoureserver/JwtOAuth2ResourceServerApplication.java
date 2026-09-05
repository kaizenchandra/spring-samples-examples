package com.synechisveltiosi.jwtouth2resoureserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class JwtOAuth2ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtOAuth2ResourceServerApplication.class, args);
    }

}
