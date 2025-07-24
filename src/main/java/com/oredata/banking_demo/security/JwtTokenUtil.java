package com.oredata.banking_demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    private final Key key;
    private final long jwtExpirationMs;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expirationMs}") long jwtExpirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;
        log.info("JWT utility initialized with expiration: {} ms", jwtExpirationMs);
    }

    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setId(userDetails.getUserId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        log.debug("Generated JWT token for user: {}", userDetails.getUsername());
        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = parseClaims(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("Error extracting username from token: {}", e.getMessage());
            throw new RuntimeException("Invalid token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = parseClaims(token);

            // Token'ın süresinin dolup dolmadığını kontrol et
            Date expiration = claims.getBody().getExpiration();
            if (expiration.before(new Date())) {
                log.warn("Token has expired");
                return false;
            }

            log.debug("Token validation successful");
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.warn("JWT token is malformed: {}", e.getMessage());
            return false;
        } catch (SecurityException e) {
            log.warn("JWT signature validation failed: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.warn("JWT token compact of handler are invalid: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error during token validation: {}", e.getMessage());
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // Token'dan user ID'yi almak için yardımcı method
    public String getUserIdFromToken(String token) {
        try {
            Claims claims = parseClaims(token).getBody();
            return claims.getId();
        } catch (Exception e) {
            log.error("Error extracting user ID from token: {}", e.getMessage());
            throw new RuntimeException("Invalid token", e);
        }
    }

    // Token'ın ne kadar süre kaldığını öğrenmek için
    public Date getExpirationDateFromToken(String token) {
        try {
            Claims claims = parseClaims(token).getBody();
            return claims.getExpiration();
        } catch (Exception e) {
            log.error("Error extracting expiration date from token: {}", e.getMessage());
            throw new RuntimeException("Invalid token", e);
        }
    }
}