package com.smartinvestment.smart_investment_portfolio.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    public static final String SECRET_KEY = "kakjkfdjaidwouaoeiurihalknfcfklajrwioureoqwewq";
    public static final long EXPIRATION = 1000 * 60 * 15;
    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token){
        return parseToken(token).getSubject();
    }
    public String extractRole(String token){
        return parseToken(token).get("role").toString();
    }
    public boolean validateToken(String token){
        try {
            parseToken(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
    public Claims parseToken(String token){
      return Jwts.parserBuilder()
              .setSigningKey(getSigninKey())
              .build()
              .parseClaimsJws(token)
              .getBody();
    }
}
