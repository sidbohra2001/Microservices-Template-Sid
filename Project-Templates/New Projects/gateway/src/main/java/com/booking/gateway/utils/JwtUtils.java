package com.booking.gateway.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtils {

    private final static String SECRET = "1254512535SDSDSDSDFFSDFAD654654F1254512535SDSDSDSDFFSDFAD654654F";

    public String validate(String token) throws ExpiredJwtException {
        if(token != null && token.startsWith("Bearer ")) token = token.substring(7);
        return String.valueOf(Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().get("roles"));
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

}
