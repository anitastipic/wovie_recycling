package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtEncoder jwtEncoder;

    public UserController(UserService userService, AuthenticationProvider authenticationProvider, JwtEncoder jwtEncoder) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/register")
    public User registerNewUserAccount(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @PostMapping("/login")
    private Optional<String> login(@RequestBody User user,  @Value("${tokens.algorithm}") MacAlgorithm macAlgorithm, HttpServletResponse response) {
        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        var authenticatedAuthentication = this.authenticationProvider.authenticate(usernamePasswordAuthentication);

        if (authenticatedAuthentication.isAuthenticated()) {
            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(macAlgorithm).build(),
                    JwtClaimsSet.builder()
                            .subject(authenticatedAuthentication.getName())
                            .build()));

            Cookie cookie = new Cookie("auth_token", token.getTokenValue());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return Optional.of(token.getTokenValue());
        }
        return Optional.empty();
    }
}
