package com.practice.store.service;

import com.practice.store.exception.ResourceNotFoundException;
import com.practice.store.model.UserEntity;
import com.practice.store.repository.UserRepository;
import com.practice.store.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserEntity getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found for username: " + username));
    }

}
