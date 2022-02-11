package com.example.service;

import com.example.dto.UserDto;
import com.example.model.ChangePassword;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public UserDto saveUser(UserDto userDto) throws Exception {
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
            userRepository.save(user);
            return userDto;
        }).orElseThrow(() -> new Exception("Role not supported"));
    }

    @Override
    public User updateUser(Long id, UserDto userDto) throws Exception {
        User user = userRepository.getById(id);

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Wrong email!!");

        return user.get();
    }

    @Override
    public List<UserDto> getAllUsers() throws NotFoundException {
        return userRepository.getAllUsers().get();
    }

    @Override
    public User changePassword(ChangePassword changePassword, Long id) throws Exception {
        User user = userRepository.getById(id);

        if(passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())) {

            user.setPassword(passwordEncoder.encode(changePassword.getPassword()));
            return userRepository.save(user);
        }
        else {
            throw new Exception("Wrong current password!");
        }

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
    public void deleteUser(Long id) { userRepository.deleteById(id);}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
