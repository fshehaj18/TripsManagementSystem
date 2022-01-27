package com.example.web.controller;

import com.example.model.AuthenticationRequest;
import com.example.model.AuthenticationResponse;
import com.example.service.MyUserDetailsService;
import com.example.service.UserServiceImpl;
import com.example.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserServiceImpl userService;

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        logger.debug("Entered username is: " + authenticationRequest.getUsername()); // logger
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));



        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtTokenUtil.generateToken(authenticationRequest.getUsername()),
                userService.findByEmail(authenticationRequest.getUsername()).getRole().getRoleName(),
                userService.findByEmail(authenticationRequest.getUsername()).getId());
    logger.debug("User " + authenticationRequest.getUsername() + " successfully authenticated.");
        return ResponseEntity.ok().body(authenticationResponse);
    }


}
