package com.uscboard.dashboard.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.uscboard.dashboard.exception.UserNotFoundException;
import com.uscboard.dashboard.model.Role;
import com.uscboard.dashboard.model.User;
import com.uscboard.dashboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findUserByEmail(email);
        
        org.springframework.security.core.userdetails.User springUser = null;
        if(userOpt.isEmpty()){
            throw new UsernameNotFoundException("User with email: "+email+ " not found");
        }else{
            User user = userOpt.get();
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> ga = new HashSet<>();
            for(Role role : roles){
                ga.add(new SimpleGrantedAuthority(role.getName()));
            }
            springUser = new org.springframework.security.core.userdetails.User(
                email, user.getPassword(), ga );
        }
        return springUser;
    }
    
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{
        User user = userRepo.findEmail(email);
             if(user !=null){
                 user.setResetPasswordToken(token);
                 userRepo.save(user);
             }else{
                 throw new UserNotFoundException("Could not find any user with the email"+email);
             }
    }
    public User getByresetPasswordToken(String token){
            return userRepo.findByResetPasswordToken(token);
    }
    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepo.save(user);
    }
}
