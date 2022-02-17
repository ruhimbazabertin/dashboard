package com.uscboard.dashboard.repository;

import com.uscboard.dashboard.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name); 
}
