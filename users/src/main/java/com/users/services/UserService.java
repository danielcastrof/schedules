package com.users.services;

import org.springframework.stereotype.Service;

import com.users.models.UserModel;
import com.users.producers.UserProducer;
import com.users.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;

    final UserProducer userProducer;

    UserService(UserProducer userProducer, UserRepository userRepository) {
        this.userProducer = userProducer;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel saveUser(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
