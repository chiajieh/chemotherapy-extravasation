package com.mobileapp.chemotherapyextravasation.security;

import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    /**
     * Utility method to generate JWT token
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    /**
     * Method to decode and return the JWT Secret key
     * @return
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Method to get username from JWT token
     * @param token
     * @return
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    /**
     * Method to validate JWT token
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ChemotherapyExtravasationAPIException("Expired JWT token");
        } catch (MalformedJwtException e) {
            throw new ChemotherapyExtravasationAPIException("Invalid JWT token");
        } catch (UnsupportedJwtException e) {
            throw new ChemotherapyExtravasationAPIException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new ChemotherapyExtravasationAPIException("JWT claims string is empty.");
        }

    }


}
