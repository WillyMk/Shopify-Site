package com.example.productservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.productservice.user.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
// JwtService class provides methods to generate and retrieve information from JWTs.
// It uses a specified algorithm, key, issuer, and expiration time to create and validate JWTs.
// This service can be injected into other components (such as controllers or other services) to handle JWT-related operations
// in a Spring application.
@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJwt(User user) {
        return JWT.create()
                .withClaim("USERNAME", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        return JWT.decode(token).getClaim("USERNAME").asString(); //we use asString to know the claim is a string
    }
}
