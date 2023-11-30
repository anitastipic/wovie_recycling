package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.repository.UserRepository;
import com.codecool.wovie_recycling.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;

    public UserController(UserService userService, AuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping("/register")
    public User registerNewUserAccount(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    private boolean login(@RequestBody User user) {
        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        var authenticatedAuthentication = this.authenticationProvider.authenticate(usernamePasswordAuthentication);
        return authenticatedAuthentication.isAuthenticated();
    }
}
