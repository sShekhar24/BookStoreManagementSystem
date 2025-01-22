package com.library.security;

import java.io.IOException;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class RateLimitConfig {

    @Bean
    public OncePerRequestFilter rateLimiterFilter() {
        return new OncePerRequestFilter() {
            // Initialize a bucket with a limit of 5 requests per minute
            private final Bucket bucket = Bucket4j.builder()
                    .addLimit(Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1))))
                    .build();

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                // Check if the request can be consumed (i.e., within the rate limit)
                if (bucket.tryConsume(1)) {
                    filterChain.doFilter(request, response);  // Allow the request to proceed
                } else {
                    // If the rate limit is exceeded, respond with status 429
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    response.getWriter().write("Too many requests, please try again later.");
                }
            }
        };
    }
}
