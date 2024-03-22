package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationProvider authenticationProvider;
    private final JwtEncoder jwtEncoder;

    public AuthenticationController(AuthenticationProvider authenticationProvider, JwtEncoder jwtEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public Optional<String> login(@RequestBody User user, @Value("${tokens.algorithm}") MacAlgorithm macAlgorithm, HttpServletResponse response) {
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
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            return Optional.of(token.getTokenValue());
        }
        return Optional.empty();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth_token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
