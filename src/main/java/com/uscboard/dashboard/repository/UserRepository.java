package com.uscboard.dashboard.repository;


import java.util.Optional;

import com.uscboard.dashboard.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
 Optional<User> findUserByEmail(String email);
}
