package com.uscboard.dashboard.service;

import java.util.Optional;

import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.repository.UserRepository;
import com.uscboard.dashboard.security.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    UserRepository userRepo; 

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<User> user = userRepo.findUserByEmail(email);
         user.orElseThrow(() -> new UsernameNotFoundException("Not found" + email));
        return user.map(MyUserDetails::new).get();
    }
    
}
