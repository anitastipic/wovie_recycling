package com.codecool.wovie_recycling.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                 .build();
     }
}
