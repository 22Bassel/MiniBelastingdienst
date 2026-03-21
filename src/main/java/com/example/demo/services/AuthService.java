package com.example.demo.services;

import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {


    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private CustomUserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    public void AuthControlleren(String Username,String Password){

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(Username,Password));

    }

    public String TokenMaker(String userName){
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .toList();

        return jwtUtil.generateToken(userDetails.getUsername(), roles);


    }
}
