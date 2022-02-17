package com.uscboard.dashboard.service;

import java.util.List;

import com.uscboard.dashboard.model.Role;
import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.repository.RoleRepository;
import com.uscboard.dashboard.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    @Override
    public User saveUser(User user) {
            
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
 
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String name) {
        
        User user = userRepo.findUserByEmail(email);
        Role role = roleRepo.findRoleByName(name);
        
        
    }

    @Override
    public User getUsers(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return null;
    }

}
    
}
