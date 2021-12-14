package com.example.service.service;

import com.example.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userServiceImpl.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User getUserByEmail(String email) {
        return userServiceImpl.findByEmail(email);
    }

}
