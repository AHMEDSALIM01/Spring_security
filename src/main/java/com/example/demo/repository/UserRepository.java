package com.example.demo.repository;

import com.example.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Override
    Optional<Users> findById(Long aLong);

    Users findByUsername(String username);

    Users findByEmail(String Email);
}
