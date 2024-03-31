package com.example.onlinefoodstorage.security;

import com.example.onlinefoodstorage.dtos.JwtDto;
import com.example.onlinefoodstorage.enums.TokenType;
import com.example.onlinefoodstorage.exceptions.InvalidJwtException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Base64;
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

    private final ObjectMapper objectMapper;

    public String generateToken(UserDetails userDetails) {
        return this.generateToken(userDetails, tokenValidityTime);
    }

    public String generateToken(UserDetails userDetails, long validityTime) {
        return this.generateToken(userDetails, validityTime, TokenType.AUTH);
    }

    public String generateToken(UserDetails userDetails, long validityTime, TokenType type) {
        Set<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        Date expiration = new Date(new Date().getTime() + validityTime * 1000);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", authorities)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public String generateToken(String subject, long validityTimeInMin) {
        Date expiration = new Date(new Date().getTime() + validityTimeInMin * 60000);
        return Jwts.builder()
                .setSubject(subject)
                .claim("type", TokenType.TEMPORARY)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }


    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretWord);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void checkArticleToken(String token) {
        Claims claims = this.getClaims(token);

        // Date issuedAt = claims.getIssuedAt();
        // Date now = new Date();
        // long differance = (now.getTime() - issuedAt.getTime()) / 1000;
        // if (differance < 3) {
        // throw new ExpiredArticleTokenException("Token erta jo`natilgan");
        // }
    }

    public <T extends JwtDto> T decodeThirdPartyToken(String token, Class<T> valueType) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();

            String payload = new String(decoder.decode(chunks[1]));
            T value = objectMapper.readValue(payload, valueType);

            Date now = new Date();
            long iat = value.getIat();
            Date issuedAt = new Date(iat * 1000);
            if (issuedAt.after(now)) {
                throw new InvalidJwtException("Token xato");
            }
            long exp = value.getExp();
            Date expiresAt = new Date(exp * 1000);
            if (expiresAt.before(now)) {
                throw new InvalidJwtException("Token eskirgan");
            }

            return value;
        } catch (JsonProcessingException e) {
            throw new InvalidJwtException("Token xato");
        }
    }

//    @Value("${jwt.access.token.validity}")
//    private long ACCESS_TOKEN_VALIDITY_TIME;
//
//    @Value("${jwt.refresh.token.validity}")
//    private long REFRESH_TOKEN_VALIDITY_TIME;
//
//    @Value("${jwt.access.key}")
//    private String JWT_ACCESS_KEY;
//
//    @Value("${jwt.refresh.key}")
//    private String JWT_REFRESH_KEY;
//
//    public synchronized String generateAccessToken(
//            UserDetails userDetails
//    ) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + ACCESS_TOKEN_VALIDITY_TIME))
//                .claim("authorities", userDetails.getAuthorities())
//                .signWith(getSigningKey())
//                .compact();
//    }
//
//    private Key getSigningKey() {
//        byte[] bytes = Decoders.BASE64URL.decode(this.JWT_ACCESS_KEY);
//        return Keys.hmacShaKeyFor(bytes);
//    }
//
//    public Claims getClaims(String token) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public boolean isValid(String token) {
//        Claims claims = getClaims(token);
//        return claims != null;
//    }

}
