package com.uscboard.dashboard.service;

import java.util.List;

import com.uscboard.dashboard.model.Role;
import com.uscboard.dashboard.model.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userEmail, String roleName);
    User getUsers(String userEmail);
    List<User>getUsers();
}
