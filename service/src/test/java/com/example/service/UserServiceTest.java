package com.example.service;

import com.example.service.model.User;
import com.example.service.repository.RoleRepository;
import com.example.service.repository.UserRepository;
import com.example.service.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.UUID;

public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;


    @Test
    public void get_users(){

        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail( UUID.randomUUID().toString() + "@edu.al");
        user.setPassword("test");
        user.setRole(roleRepository.getById(1));


        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        // user.setRole(1);
    }
        }
