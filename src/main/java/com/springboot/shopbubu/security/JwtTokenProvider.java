package com.springboot.shopbubu.security;


import com.springboot.shopbubu.dto.response.TokenResponse;
import com.springboot.shopbubu.entity.RoleEntity;
import com.springboot.shopbubu.utils.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.springboot.shopbubu.utils.TokenType.ACCESS_TOKEN;
import static com.springboot.shopbubu.utils.TokenType.REFRESH_TOKEN;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private long jwtExpirationDate;

    public String generateToken(CustomUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        var expirationThirtyMinutes = new Date(System.currentTimeMillis() + 1000 * jwtExpirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .claim("role", user.getUser().getRoles().stream().map(RoleEntity::getRoleName).toList())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationThirtyMinutes)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isValid(String token, TokenType type, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateRefreshToken(UserDetails user) {
        return generateRefreshToken(new HashMap<>(), user);
    }
    private String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * jwtExpirationDate))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes); 
    }
//    private Key getKey(TokenType type) {
//        if (ACCESS_TOKEN.equals(type))
//            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessKey));
//        else
//            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));
//    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
