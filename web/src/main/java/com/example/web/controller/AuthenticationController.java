package com.example.web.controller;

import com.example.service.model.AuthenticationRequest;
import com.example.service.model.AuthenticationResponse;
import com.example.service.service.MyUserDetailsService;
import com.example.service.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        logger.info("Entered username is: " + authenticationRequest.getUsername()); // logger
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), userDetailsService.getUserByEmail(authenticationRequest.getUsername()).getPassword())) {
            throw new Exception("Wrong credentials! Authentication failed.");
        }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtTokenUtil.generateToken(authenticationRequest.getUsername()));

        return ResponseEntity.ok().body(authenticationResponse);
    }


}
