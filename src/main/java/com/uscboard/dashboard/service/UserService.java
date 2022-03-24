package com.uscboard.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public void createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);


    }
    public User findLoggedUser(String username){

        User user = userRepo.findEmail(username);
        return user;
    }
    public List<User> findAllUsers(){

        return userRepo.findAll();
    }
    public Optional<User> findUserById(int id){

        return userRepo.findById(id);
    }
    public void deleteUser(User user){

         userRepo.delete(user);
    }
}
