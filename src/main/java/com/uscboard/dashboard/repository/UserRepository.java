package com.uscboard.dashboard.repository;


import com.uscboard.dashboard.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
 User findUserByEmail(String email);
}
