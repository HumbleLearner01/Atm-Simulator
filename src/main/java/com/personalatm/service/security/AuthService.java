package com.personalatm.service.security;

import com.personalatm.config.jwt.JwtUtil;
import com.personalatm.config.security.UserDetailsServiceImpl;
import com.personalatm.helper.payload.security.AuthenticationResponse;
import com.personalatm.helper.payload.security.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationResponse login(LoginRequest loginRequest) {
        String token = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getAccountNumber(), loginRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getAccountNumber());
            token = jwtUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new AuthenticationResponse(loginRequest.getAccountNumber(), token);
    }
}