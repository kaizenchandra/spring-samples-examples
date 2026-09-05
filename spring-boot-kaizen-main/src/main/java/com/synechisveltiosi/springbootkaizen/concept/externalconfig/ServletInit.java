package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletInit {

    @Value("${servlet.max-users}")
    private String maxUsers;

    @Value("${servlet.mode}")
    private String mode;

    @Bean
    public ServletRegistrationBean<HttpServlet> servletRegistrationBean() {
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>(new MyServlet(), "/custom");
        registrationBean.addInitParameter("maxUsers", "10");
        registrationBean.addInitParameter("mode", "production");
        return registrationBean;
    }
}
