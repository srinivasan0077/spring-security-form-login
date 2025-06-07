package com.security.demo.service;

import com.security.demo.entities.User;
import com.security.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

}
