package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagementService {
    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }




}
