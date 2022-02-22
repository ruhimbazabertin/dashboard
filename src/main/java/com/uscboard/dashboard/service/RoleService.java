package com.uscboard.dashboard.service;

import java.util.Optional;

import com.uscboard.dashboard.model.Role;
import com.uscboard.dashboard.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;
    public void createRole(Role role){
        roleRepo.save(role);
    }
    public Role findRoleById(int id){
       Role role =  roleRepo.getById(id);
       return role;
    }

}
