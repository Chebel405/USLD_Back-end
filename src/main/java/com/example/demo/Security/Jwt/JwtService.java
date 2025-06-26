package com.example.demo.Security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Service responsable de la création et validation des tokens JWT.
 */
@Service
public class JwtService {

    // Clé secrète pour signer le token (en réalité, stocke ça dans un fichier sécurisé !)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Génère un JWT pour l'email donné.
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)                      // Identifiant principal dans le token
                .setIssuedAt(new Date(System.currentTimeMillis()))   // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(key)                          // Signature avec la clé secrète
                .compact();
    }

    /**
     * Extrait l'email de l'utilisateur (subject) depuis le token JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Vérifie si le token est toujours valide.
     */
    public boolean isTokenValide(String token, UserDetails userDetails) {
        String extractedEmail = extractUsername(token);
        return (extractedEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * Vérifie la date d’expiration du token.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * Méthode générique pour extraire une information du token (comme l’email, la date…).
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
