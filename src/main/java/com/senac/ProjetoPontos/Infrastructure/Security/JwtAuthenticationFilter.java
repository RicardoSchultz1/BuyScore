package com.senac.ProjetoPontos.Infrastructure.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@org.springframework.lang.NonNull HttpServletRequest request, @org.springframework.lang.NonNull HttpServletResponse response, @org.springframework.lang.NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authHeader == null) {
            logger.debug("No Authorization header present");
        } else {
            String header = authHeader.trim();
            // accept case-insensitive 'Bearer ' prefix
            if (header.length() < 7 || !header.regionMatches(true, 0, "Bearer ", 0, 7)) {
                // log first 40 chars (masked) to help debugging without dumping full token
                String snippet = header.length() <= 40 ? header : header.substring(0, 40) + "...";
                logger.debug("Authorization header not using 'Bearer ' prefix: {}", snippet);
            } else {
                jwt = header.substring(7).trim();
                try {
                    username = jwtUtil.extractUsername(jwt);
                } catch (Exception ex) {
                    logger.debug("Failed to extract username from JWT: {}", ex.toString());
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            boolean valid = jwtUtil.isTokenValid(jwt);
            if (valid) {
                logger.debug("JWT valid for user='{}', authorities='{}'", userDetails.getUsername(), userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                logger.debug("JWT invalid for user='{}'", username);
            }
        } else {
            if (username == null) logger.debug("No username extracted from JWT; skipping authentication");
            else logger.debug("SecurityContext already contains authentication: {}", SecurityContextHolder.getContext().getAuthentication());
        }
        filterChain.doFilter(request, response);
    }
}
