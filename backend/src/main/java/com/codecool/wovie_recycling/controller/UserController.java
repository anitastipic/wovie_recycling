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
    private final AuthenticationProvider authenticationProvider;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public UserController(UserService userService, AuthenticationProvider authenticationProvider, JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth_token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // This effectively clears the cookie
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }

    private String extractUsernameFromToken(String token) {
        var jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString("sub");
    }
}
