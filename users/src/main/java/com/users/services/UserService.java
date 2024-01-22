package com.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.models.UserModel;
import com.users.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel saveUser(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
