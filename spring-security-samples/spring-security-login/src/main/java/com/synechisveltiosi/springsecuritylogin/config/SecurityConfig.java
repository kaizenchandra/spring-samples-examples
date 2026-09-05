package com.synechisveltiosi.springsecuritylogin.config;

import com.synechisveltiosi.springsecuritylogin.filter.JWTRequestFilter;
import com.synechisveltiosi.springsecuritylogin.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests.requestMatchers("/owner/**").hasAnyRole("ADMIN")
                                .requestMatchers("/manager/**").hasAnyRole("MANAGER")
                                .requestMatchers("/tenant/**").hasRole("TENANT")
                                        .requestMatchers("/public/**","/auth/sign-in").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JWTRequestFilter jwtRequestFilter() {
        return new JWTRequestFilter(jwtService(), userDetailsService(passwordEncoder()));
    }

    @Bean
    public JWTService jwtService() {
        return new JWTService();
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails tenant = User.withUsername("tenant").password(passwordEncoder.encode("tenant"))
                .roles("TENANT")
                .build();
        UserDetails admin = User.withUsername("owner").password(passwordEncoder.encode("owner"))
                .roles("TENANT", "MANAGER", "ADMIN")
                .build();
        UserDetails manager = User.withUsername("manager").password(passwordEncoder.encode("manager"))
                .roles("TENANT", "MANAGER")
                .build();
        List<UserDetails> users = List.of(tenant, manager, admin);
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }


}
