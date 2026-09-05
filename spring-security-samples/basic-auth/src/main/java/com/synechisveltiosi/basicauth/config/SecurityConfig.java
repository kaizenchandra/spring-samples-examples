package com.synechisveltiosi.basicauth.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {


/*
    So if you set sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS), then:
	•	✅ HTTP Basic will still work.
	•	❌ Form login will break, because there’s no session to keep you logged in after submitting the form.
	 use SessionCreationPolicy.IF_REQUIRED TO WORK BOTH
*/

/*
    If we enable CSRF then ->
    Method                              Form Login                                     HTTP Basic
    GET                                 ✅  Works                                       ✅  Works
    POST (e.g., login form)             ✅  Works (token is auto-included in form)      ❌ Will fail unless CSRF token is sent
    PUT/DELETE                          ✅  Works (with CSRF token)                     ❌ Fails (unless you manually handle CSRF)

    If we disable csrf then -> both form-login and basic-auth will work
*/
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers("/login","/sign-in","/logout","/unsecured","/basic/**")
                                        .permitAll().anyRequest().authenticated())
                        .httpBasic(httpBasicConfigurer ->httpBasicConfigurer.authenticationEntryPoint(basicAuthenticationFilter()))
                        .formLogin(Customizer.withDefaults())
                        .authenticationManager(authenticationManager(userDetailsService(passwordEncoder()),passwordEncoder()))
                        .rememberMe(Customizer.withDefaults())
                        .logout(Customizer.withDefaults())
                        .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                        .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                        .exceptionHandling(Customizer.withDefaults())
                        .build();
    }

    @Bean
    public BasicAuthenticationEntryPoint basicAuthenticationFilter() {
        BasicAuthenticationEntryPoint basicAuthenticationFilter = new BasicAuthenticationEntryPoint();
        basicAuthenticationFilter.setRealmName("MyApp");
        return basicAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }


    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> users = Stream.of("admin", "user")
                .map(user -> User.builder().username(user)
                        .password(passwordEncoder.encode("password"))
                        .roles(user.toUpperCase()).build())
                .toList();
        return new InMemoryUserDetailsManager(users);
    }

    /*
    @Autowired
    public void configure(AuthenticationManagerBuilder builder) {
        builder.eraseCredentials(false);
    }
     */


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
