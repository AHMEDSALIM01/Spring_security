package com.example.demo.service;

import com.example.demo.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Users> getOnById(Long id);
    Users addUser(Users user);
    Users updateUser(Long id, Users user);
    Users getUser(String username);

    List<Users> getUsers();

    UserDetails findByEmail(String email);
}
