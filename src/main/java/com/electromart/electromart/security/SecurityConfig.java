package com.electromart.electromart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * The type Security config.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application, allowing all requests
     * and adding a custom filter after the CSRF filter.
     *
     * @param http the HTTP security configuration builder
     * @return the configured security filter chain
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

            // Configures authorization for all HTTP requests
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            )

            // Adds a custom filter after the CSRF filter
            .addFilterAfter(
                new CsrfLoggerFilter(), CsrfFilter.class
            )

            // Builds and returns the security filter chain
            .build();
    }
}
