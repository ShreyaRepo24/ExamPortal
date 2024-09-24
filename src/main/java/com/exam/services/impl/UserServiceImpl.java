package com.exam.services.impl;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{

        User local=this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            logger.info("User is already there!!");
            throw new Exception("User already present");
        }else{
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local=this.userRepository.save(user);

        }

        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    //updating the existing user
    @Override
    public User updateUser(Long userId, User updatedUser) throws Exception {
        // Fetch the user by ID
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id: " + userId));

        // Update the fields of the existing user
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        // Save and return the updated user
        return this.userRepository.save(existingUser);
    }


    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
