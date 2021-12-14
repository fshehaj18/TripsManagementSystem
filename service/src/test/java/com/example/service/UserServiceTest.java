package com.example.service;

import com.example.service.dto.UserDto;
import com.example.service.model.User;
import com.example.service.repository.RoleRepository;
import com.example.service.repository.UserRepository;
import com.example.service.service.UserService;
import com.example.service.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    void setup() throws Exception{
    MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_users() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setEmail(UUID.randomUUID().toString() + "@edu.al");
        userDto.setPassword("test");
        userDto.setRoleId(1);

        User user = new User();

        ArrayList<User> users = new ArrayList<>();

        //users.add(user);

        // user.setRole(1);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));


    }

}
