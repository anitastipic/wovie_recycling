package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserManagementService userManagementService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, UserManagementService userManagementService, BCryptPasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User createNewUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setUsername(user.getUsername());
        return userManagementService.saveUser(user);
    }


    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }


}
