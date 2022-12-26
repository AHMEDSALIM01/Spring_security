package com.example.demo.service.Implementation;

import com.example.demo.domain.Role;
import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
@Autowired
UserRepository userRepository;



    @Override
    public Optional<Users> getOnById(Long id) {
        return userRepository.findById(id);
    }

   @Override
    public Users addUser(Users user) {
        Pattern patternEmail = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$");
        Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        if (user.getEmail()!=null && user.getPassword() !=null && user.getPhone() !=null){
            Matcher matcherEmail = patternEmail.matcher(user.getEmail());
            Matcher matcherPassword = patternPassword.matcher(user.getPassword());
            if(matcherEmail.matches() && matcherPassword.matches()) {
                Optional<Users> userByEmail = userRepository.findById(user.getId());
                if (userByEmail.isPresent()) {
                    throw new IllegalStateException("Email existe déja");
                } else {
                    user.setRole(new Role(Long.valueOf(1)));
                    return userRepository.save(user);
                }
            }else{
                throw new IllegalStateException("Email or password Format Invalid");
            }
        }else{
            throw new IllegalStateException("il faut remplire tous les champs");
        }
    }
    @Override
    public Users updateUser(Long id, Users user) {
        Users userById = userRepository.findById(id).orElse(null);
        Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        if(userById != null){
            if (user.getEmail()!=null && user.getPassword() !=null && user.getPhone() !=null){
                Matcher matcherPassword = patternPassword.matcher(user.getPassword());
                if(userById.getEmail().equals(user.getEmail()) && matcherPassword.matches()) {
                    userById.setEmail(user.getEmail());
                    userById.setPassword(user.getPassword());
                    userById.setPhone(user.getPhone());
                    return userById;
                }else{
                    if(!userById.getEmail().equals(user.getEmail())){
                        throw new IllegalStateException("email address cannot be changed");
                    }else{
                        throw new IllegalStateException("password Format Invalid");
                    }
                }
            }else{
                throw new IllegalStateException("fill all the inputs");
            }
        }else{
            throw new IllegalStateException("id doesnt exist !");
        }
    }

    @Override
    public Users getUser(String username){
        return null;
    }

    @Override
    public List<Users> getUsers() {
        return null;
    }

    @Override
    public UserDetails findByEmail(String email){
       Users users = userRepository.findByEmail(email);

        User user = new  User(users.getEmail(), new BCryptPasswordEncoder().encode(users.getPassword()), Collections.singleton(new SimpleGrantedAuthority(users.getRole().getName())));
        return user;
    }
}
