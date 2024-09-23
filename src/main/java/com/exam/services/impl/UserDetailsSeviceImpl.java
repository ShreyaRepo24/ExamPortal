package com.exam.services.impl;

import com.exam.models.User;
import com.exam.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSeviceImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByUsername(username);
        if(user==null){
          logger.info("User not found");
          throw new UsernameNotFoundException("No user found");
        }

        return user;
    }
}
