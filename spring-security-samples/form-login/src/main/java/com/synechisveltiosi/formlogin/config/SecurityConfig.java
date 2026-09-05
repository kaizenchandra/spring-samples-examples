package com.synechisveltiosi.formlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    // if we don't enable form login in security filter chain then it will show error access to localhost was denied
    // if set our own user details service bean then this will override the user details declared in application.yml file
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests.requestMatchers("/login","/logout","/unsecured")
                                        .permitAll()
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .rememberMe(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }



    @Bean
     public UserDetailsService inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        List<UserDetails> list = Stream.of("user", "admin")
                .map(user ->
                        User.builder()
                                .username(user)
                                .password(passwordEncoder.encode("password"))
                                .roles(user.toUpperCase())
                                .build())
                .toList();
        return new InMemoryUserDetailsManager(list);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
