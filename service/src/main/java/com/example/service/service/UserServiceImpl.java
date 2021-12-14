package com.example.service.service;

import com.example.service.dto.UserDto;
import com.example.service.model.User;
import com.example.service.repository.RoleRepository;
import com.example.service.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(UserDto userDto) throws Exception {
        User user = new User();
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) { //email already exists
            throw new Exception("Email already exists!!");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return roleRepository.findById(userDto.getRoleId()).map(role -> {
            user.setRole(role);
            return userRepository.save(user);
        }).orElseThrow(() -> new Exception("Role not found"));
    }

    @Override
    public User updateUser(Long id, UserDto userDto) throws Exception {
        User user = userRepository.getById(id);


        return saveUser(userDto);
    }

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Wrong email!!");

        return user.get();
    }

    @Override
    public List<User> getAllUsers() throws NotFoundException {
        return userRepository.findAll();
    }

    @Override
    public User changePassword(String password, Long id) {
        User user = userRepository.getById(id);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }


}
