package com.example.service.service;

import com.example.service.dto.UserDto;
import com.example.service.model.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService {

    UserDto saveUser(UserDto userDto) throws Exception;

    User updateUser(Long id, UserDto userDto) throws Exception;

    User findByEmail(String email) throws UsernameNotFoundException;

    List<User> getAllUsers() throws NotFoundException;

    User changePassword(String password, Long id);

    User findById(Long id);


}
