package com.electromart.electromart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The type Security config.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     * Allows all requests to be permitted and disables CSRF protection.
     *
     * @param http The HttpSecurity object to configure security settings.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring security settings.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure authorization to permit all requests.
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                .permitAll())
            // Disable CSRF protection.
            .csrf(AbstractHttpConfigurer::disable);
        // Build and return the configured SecurityFilterChain.
        return http.build();
    }
}
