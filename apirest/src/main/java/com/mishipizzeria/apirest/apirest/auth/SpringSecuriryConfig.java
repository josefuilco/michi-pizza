package com.mishipizzeria.apirest.apirest.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecuriryConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/productos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/productos/**").permitAll()
                        .anyRequest().authenticated()
                // .requestMatchers("/producto/**").authenticated()
                // .requestMatchers("/categoria/**").hasRole("ADMIN").anyRequest().authenticated()
                )

                .sessionManagement(managmet -> managmet.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}

// @Bean
// public PasswordEncoder passwordEncoder(){
// return new BCryptPasswordEncoder();
// }
