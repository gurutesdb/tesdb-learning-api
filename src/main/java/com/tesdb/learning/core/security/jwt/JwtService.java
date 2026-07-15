package com.tesdb.learning.core.security.jwt;

import com.tesdb.learning.core.constant.JwtConstants;
import com.tesdb.learning.core.enums.UserType;
import com.tesdb.learning.core.security.model.CustomUserDetails;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService
{
    private final JwtProperties jwtProperties;

    /**
     * Generate Access Token
     */
    public String generateAccessToken(CustomUserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        claims.put(JwtConstants.USER_TYPE, userDetails.getUserType().name());

        claims.put(JwtConstants.UUID, userDetails.getUuid().toString());

        return createToken(
                claims,
                userDetails.getUsername(),
                jwtProperties.getAccessTokenExpiration()
        );
    }

    /**
     * Generate Refresh Token
     */
    public String generateRefreshToken(CustomUserDetails userDetails) {

        return createToken(
                new HashMap<>(),
                userDetails.getUsername(),
                jwtProperties.getRefreshTokenExpiration()
        );
    }

    /**
     * Create JWT
     */
    private String createToken(
            Map<String, Object> claims,
            String subject,
            long expiration) {

        Date now = new Date();

        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extract Email
     */
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract UUID
     */
    public String extractUuid(String token) {

        return extractAllClaims(token)
                .get(JwtConstants.UUID, String.class);
    }

    /**
     * Extract User Type
     */
    public UserType extractUserType(String token) {

        String value = extractAllClaims(token)
                .get(JwtConstants.USER_TYPE, String.class);

        return UserType.valueOf(value);
    }

    /**
     * Validate Token
     */
    public boolean isTokenValid(
            String token,
            CustomUserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /**
     * Check Expiration
     */
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    /**
     * Extract Expiration
     */
    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generic Claim Reader
     */
    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }

    /**
     * Read Claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Secret Key
     */
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    public Long getAccessTokenExpiration()
    {
        return jwtProperties.getAccessTokenExpiration();
    }

}
