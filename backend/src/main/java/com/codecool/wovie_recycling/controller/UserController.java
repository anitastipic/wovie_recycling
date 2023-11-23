package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.repository.UserRepository;
import com.codecool.wovie_recycling.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/register")
    public User registerNewUserAccount(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/login")


    private boolean emailExists(String email) {
        return userRepository.findByUsername(email) != null;
    }
}
