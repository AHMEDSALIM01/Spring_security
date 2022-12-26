package com.example.demo.dao;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Repository
public class UserDao {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(

            new User(
                    "darkLord@gmail.com",
                    new BCryptPasswordEncoder().encode("darkLord"),
                    Collections.singleton(new SimpleGrantedAuthority("Role_ADMIN"))
            ),
            new User(
                    "knight@gmail.com",
                    new BCryptPasswordEncoder().encode("knight") ,
                    Collections.singleton(new SimpleGrantedAuthority("Role_USER"))
            )
    );
public UserDetails findByEmail(String email){
    return APPLICATION_USERS.stream().filter(u -> u.getUsername().equals(email))
            .findFirst().orElseThrow(()-> new UsernameNotFoundException("user not found"));
}

}
