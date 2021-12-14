package com.example.service.repository;

import com.example.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r.roleName from Role r")
    public Optional<List<String>> getRolesByName();

}
