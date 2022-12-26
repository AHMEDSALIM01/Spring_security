package com.example.demo.rest;

import com.example.demo.domain.Users;
import com.example.demo.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/{user_id}")
    public Optional<Users> getClientById(@PathVariable Long user_id){
        Optional<Users> client = userService.getOnById(user_id);
        if(client.isPresent()){
            return client;
        }else{
            throw new IllegalStateException("Id non trouvé");
        }
    }

    @PostMapping("/add")
    public Users addUser(@RequestBody Users user){
        return userService.addUser(user);
    }

    @PutMapping("{user_id}")
    public Users updateUser(@PathVariable Long user_id,@RequestBody Users user){
        return userService.updateUser(user_id,user);
    }
}
