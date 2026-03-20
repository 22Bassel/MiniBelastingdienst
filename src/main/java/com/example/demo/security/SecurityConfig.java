package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/h2-console/**","/login","/Users/User").permitAll()
                        .requestMatchers("/Users/AlleUsers").hasRole("ADMIN")
                        .requestMatchers("/Users/**").authenticated()
                        .requestMatchers("/Belasting/InkomenBelasting/**").hasRole("USER")
                        .requestMatchers("/Belasting/**").authenticated()
                        .anyRequest().authenticated()
                )
                .userDetailsService(customUserDetailsService)
                .headers(headers-> headers.frameOptions(frame-> frame.disable())) // H2 console ( tegen ClickJacking)
                .httpBasic(httpbasic->{});
        return http.build();
    }
}
