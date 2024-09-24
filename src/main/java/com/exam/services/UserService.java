package com.exam.services;

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //update existing user
    public User updateUser(Long userId,User updatedUser) throws Exception;
    //delete user by id
    public void deleteUser(Long userId);
}
