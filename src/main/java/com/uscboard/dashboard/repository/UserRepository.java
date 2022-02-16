package com.uscboard.dashboard.repository;

import java.util.Optional;

import com.uscboard.dashboard.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}
