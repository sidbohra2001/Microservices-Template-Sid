package com.security.authentication.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private final static String SECRET = "1254512535SDSDSDSDFFSDFAD654654F1254512535SDSDSDSDFFSDFAD654654F";

    public String generateToken(String username, String roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return createToken(username, claims);
    }

    public String validateToken(String token) throws ExpiredJwtException {
        if(token != null && token.startsWith("Bearer ")) token = token.substring(7);
        return String.valueOf(Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().get("roles"));
    }

    private String createToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

}
