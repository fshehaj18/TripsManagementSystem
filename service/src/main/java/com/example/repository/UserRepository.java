package com.example.repository;

import com.example.dto.UserDto;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("select new com.example.dto.UserDto(u.id, u.firstName, u.lastName, u.email, r.roleId) from User u join Role r on u.role.roleId=r.roleId")
    Optional<List<UserDto>> getAllUsers();


}
