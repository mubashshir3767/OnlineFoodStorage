package com.example.onlinefoodstorage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.token.validity}")
    private long tokenValidityTime;

    @Value("${jwt.secret}")
    private String secretWord;


    public synchronized String generateToken(
            UserDetails userDetails
    ) {
        Set<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenValidityTime))
                .claim("authorities", authorities)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64URL.decode(this.secretWord);
        return Keys.hmacShaKeyFor(bytes);
    }


    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
