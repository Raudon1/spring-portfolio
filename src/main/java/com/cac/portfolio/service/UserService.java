package com.cac.portfolio.service;

import com.cac.portfolio.domain.Role;
import com.cac.portfolio.domain.User;

import java.util.List;

public interface UserService {
    User saveUser (User user);
    Role saveRole (Role role);
    Boolean addRoleToUser (String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
