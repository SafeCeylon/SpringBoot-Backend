package dev.safeceylon.SafeCeylon.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    // Replace with a secure and long enough key
    private static final String SECRET_KEY = "ThisisthekeyusedforencryoptionforSafeCeylondevelopmentThisisthekeyusedforencryoptionforSafeCeylondevelopment";
    private static final long EXPIRATION_TIME = 86400000; // Token validity in milliseconds (24 hours)
    private static final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS512)
                .compact();
    }
}
