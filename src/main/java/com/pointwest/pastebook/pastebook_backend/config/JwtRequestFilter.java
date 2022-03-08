package com.pointwest.pastebook.pastebook_backend.config;

import com.pointwest.pastebook.pastebook_backend.services.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// OncePerRequestFilter is a class that guarantees a single execution per request is received
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailsService;

    private final JwtToken JWT_TOKEN_UTIL;

    //Constructor
    public JwtRequestFilter(JwtToken JWT_TOKEN_UTIL) {
        this.JWT_TOKEN_UTIL = JWT_TOKEN_UTIL;
    }

    // HttpServLetRequest - is used to handle several web requests by using multiple threads
    // Filters the JWT request to check if all details are present
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // Get the JWT from the request header / authorization
        final String REQUEST_TOKEN_HEADER = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // if token is not empty
        if (REQUEST_TOKEN_HEADER != null) {

            jwtToken =  REQUEST_TOKEN_HEADER;

            // Get user from database with same username
            try {
                username = JWT_TOKEN_UTIL.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }

        } else {
            System.out.println("JWT Token is incomplete");
        }

        // If the username exists and spring security authentication data is null
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // update the spring security authentication data with the username and password
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // If JWT token is valid/not expired
            if (JWT_TOKEN_UTIL.validateToken(jwtToken, userDetails)) {

                // update the spring security authentication token with username and password
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }
        chain.doFilter(request, response);

    }
}
