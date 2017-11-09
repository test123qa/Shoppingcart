package com.virtualagent.boot.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtualagent.boot.domain.User;
import com.virtualagent.boot.repository.RoleRepository;
import com.virtualagent.boot.repository.UserRepository;

@Service
public class UserServiceAuthImpl implements UserServiceAuth {

	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Override
    public void save(User user) {
    	System.out.println("In save().....in UserServiceAuthImpl...");
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
    	System.out.println("In findByUsername()..... in UserServiceAuthImpl...");
        return userRepository.findByUserName(username);
    }
}
