package com.example.service;

import com.example.dto.UserDto;
import com.example.model.ChangePassword;
import com.example.model.User;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService {

    UserDto saveUser(UserDto userDto) throws Exception;

    User updateUser(Long id, UserDto userDto) throws Exception;

    User findByEmail(String email) throws UsernameNotFoundException;

    List<UserDto> getAllUsers() throws NotFoundException;

    User changePassword(ChangePassword password, Long id) throws Exception;

    User findById(Long id);



}
