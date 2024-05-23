package com.security.securityjwt.service;


import com.security.securityjwt.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "8ccb129f41afa105e70290fd7e5aba1fb20af0dafebeb629c146816f301f3956";

     public String extractUsername(String token) {
         return extractClaim(token, Claims::getSubject);
     }

     public boolean isValid(String token, UserDetails user) {
         String username = extractUsername(token);
         return username.equals(user.getUsername()) && !isTokenExpired(token);
     }

     private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
     }

     private Date extractExpiration(String token) {
          return extractClaim(token, Claims::getExpiration);
     }

    public <R> R extractClaim(String token, Function<Claims, R> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken (User user) {
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
