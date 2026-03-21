package com.example.demo.security;

import com.example.demo.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private JwtFilter jwtFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,JwtFilter jwtFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtFilter=jwtFilter;
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
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManager= http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManager.userDetailsService(customUserDetailsService) // CustomUserDetails
                             .passwordEncoder(passwordEncoder()); // BCrypt

        return authenticationManager.build();
    }

}
