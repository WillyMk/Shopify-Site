package com.example.productservice.security;

import com.example.productservice.user.entity.User;
import com.example.productservice.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

// This filter intercepts requests, extracts the JWT token from the Authorization header,
// validates it using jwtService, retrieves the user information, and sets up authentication using Spring Security.
// It then allows the request to continue processing.
// This is commonly used in Spring Security to authenticate users based on JWT tokens.
//filterChain is a chain of all the filters
@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization"); //checks if there is an authorization header
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){ //checks if If the header exists and starts with "Bearer ",then extracts to a  token
            String token = tokenHeader.substring(7);
            String username = jwtService.getUsername(token);
            User user = userService.getUserByUserName(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails(request)); //telling spring our authentication is okay
            SecurityContextHolder.getContext().setAuthentication(authentication); //getting the context and setting the authentication
        }
        filterChain.doFilter(request, response);
    }
}
