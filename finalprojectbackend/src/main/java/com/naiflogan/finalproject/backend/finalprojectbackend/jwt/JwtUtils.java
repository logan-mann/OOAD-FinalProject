package com.naiflogan.finalproject.backend.finalprojectbackend.jwt;

import java.util.Date;

import com.naiflogan.finalproject.backend.finalprojectbackend.database.UserEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * JWT Utils class to generate, verify, and parse JSON Web Tokens for user authentication
 * Example code from: https://www.bezkoder.com/spring-boot-jwt-authentication/ cited for creating/verifying JWTs in Java/Spring-Boot
 */
@Component
public class JwtUtils {

    //Read secret and expiration params from application config
    @Value("${auth.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.jwtExpirationMs}")
    private int jwtExpirationMs;

    //Method to generate JWT
    public String generateJwt(UserEntity user) {
        //Build and return token w/ username, sign with HS512
        return Jwts.builder()
        .setSubject(user.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }

    //Parse username from a token, return username
    public String getUsernameFromJwt(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    //Verify whether a token is valid, return true if valid, false if not
    public boolean validateJwtToken(String token) {
        try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return false;
    }

    
}
