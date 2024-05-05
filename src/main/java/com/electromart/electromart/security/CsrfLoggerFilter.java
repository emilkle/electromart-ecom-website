package com.electromart.electromart.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Csrf logger filter.
 */
public class CsrfLoggerFilter extends OncePerRequestFilter {

    /**
     * Filters incoming HTTP requests to set the CSRF token value in the response header.
     *
     * @param request     the HTTP servlet request
     * @param response    the HTTP servlet response
     * @param filterChain the filter chain to proceed with filtering
     * @throws ServletException if any servlet exception occurs during filtering
     * @throws IOException      if an I/O exception occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Retrieves the CSRF token from the request attributes
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        // Sets the CSRF token value in the response header
        response.setHeader("CSRF-TOKEN-VALUE", csrfToken.getToken());

        // Proceeds with the filter chain
        filterChain.doFilter(request, response);
    }
}
