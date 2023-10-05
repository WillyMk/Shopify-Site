package com.example.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {
    //To sum up, this configuration is effectively disabling CSRF and CORS protection and allowing all
    // requests to pass through without requiring authentication.
    // This might be useful in certain scenarios, but it's important to carefully consider the security
    // implications of such a configuration, as it essentially opens up the application to potential security risks.
    // This configuration might be suitable for a development or testing environment, but it would generally not be recommended
    // for a production environment.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
