package com.example.ticketsearchandpurchasesystem.infrastructure.security;

import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.CustomerRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "smjsfmsfhgsblsfnbluifbslfdkjhbskfdhbfdvkhasbfkvhablfhvbakjhdrbvAGBFSGbsfgBSFGBsfgbsfgBSFGnsfgNSYTNstyJdnsgbaGFBdfbsfGNBsfgNsghfnMSgNagBfsgFbsfgb";

    private Key getSignKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 часов
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) getSignKey()) // SecretKey для HS256
                .build()
                .parseSignedClaims(token) // Возвращает Jws<Claims>
                .getPayload();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
