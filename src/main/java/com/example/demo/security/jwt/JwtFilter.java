package com.example.demo.security.jwt;

import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            String username = jwtUtil.extractClaims(token).getSubject();



            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                CustomUserDetails customUserDetails= (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);


                if (jwtUtil.isTokenValid(token, customUserDetails.getUsername())) {

                    UsernamePasswordAuthenticationToken authToken =

                            new UsernamePasswordAuthenticationToken(

                                    customUserDetails, null, customUserDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }

            }

        }



        filterChain.doFilter(request, response);

    }

}

