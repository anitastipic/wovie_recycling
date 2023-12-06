package com.codecool.wovie_recycling.configuration;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtDecoder jwtDecoder, org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource) throws Exception {
         return httpSecurity
                 .csrf(AbstractHttpConfigurer::disable)
                 .cors(cors -> cors.configurationSource(corsConfigurationSource))
                 .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                 .oauth2ResourceServer(oauth -> oauth.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder)))
                 .build();
     }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

     @Bean
     public PasswordEncoder getPasswordEncoder() {
         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
     }

     @Bean
    public AuthenticationProvider getAuthenticationProvider (PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
         var authenticationProvider = new DaoAuthenticationProvider(passwordEncoder);
         authenticationProvider.setUserDetailsService(userDetailsService);
         return authenticationProvider;
     }
    @Bean
    JwtEncoder jwtEncoder(@Value("${tokens.secret}") String secret, @Value("${tokens.algorithm}") MacAlgorithm macAlgorithm) {
        var immutableSecret = new ImmutableSecret<>(new SecretKeySpec(secret.getBytes(), macAlgorithm.getName()));
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    JwtDecoder jwtDecoder(@Value("${tokens.secret}") String secret, @Value("${tokens.algorithm}") MacAlgorithm macAlgorithm) {
        var secretKey = new SecretKeySpec(secret.getBytes(), macAlgorithm.getName());
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }



}