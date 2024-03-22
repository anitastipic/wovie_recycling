package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.User;
import com.codecool.wovie_recycling.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final JwtDecoder jwtDecoder;

    public UserController(UserService userService, JwtDecoder jwtDecoder) {
        this.userService = userService;
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/register")
    public User registerNewUserAccount(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
        String token = extractTokenFromCookie(request, "auth_token");
        if (token != null) {
            String username = extractUsernameFromToken(token);
            Optional<User> user = userService.findByUsername(username);

            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookieName.equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return null;
    }

    private String extractUsernameFromToken(String token) {
        var jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString("sub");
    }
}
