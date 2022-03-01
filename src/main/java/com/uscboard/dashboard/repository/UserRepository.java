package com.uscboard.dashboard.repository;


import java.util.Optional;

import com.uscboard.dashboard.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    //Techniqual problem to resolve
 Optional<User> findUserByEmail(String email);
 @Query(value="SELECT * FROM USER u WHERE u.email = ?1", nativeQuery = true)
        User findEmail(@Param("email") String email);
 //@Query("SELECT * FROM user WHERE user.resetPasswordToken = ?")
 User findByResetPasswordToken(String token);
}
