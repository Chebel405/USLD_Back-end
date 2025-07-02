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
 * Service responsable de la génération et de la validation des tokens JWT.
 * Il permet de :
 * <ul>
 *     <li>Générer un token JWT pour un utilisateur donné</li>
 *     <li>Extraire des informations du token (comme l’email)</li>
 *     <li>Vérifier la validité d’un token (expiration, correspondance utilisateur...)</li>
 * </ul>
 */
@Service
public class JwtService {

    /**
     * Clé secrète utilisée pour signer et valider les JWT.
     * ⚠️ À sécuriser dans un fichier .env ou un Vault sécurisé en production.
     */
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Génère un token JWT basé sur l'email de l'utilisateur.
     *
     * @param email Email de l'utilisateur à inclure dans le token.
     * @return JWT signé.
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
     * Extrait le nom d'utilisateur (email) du token.
     *
     * @param token JWT reçu.
     * @return Email extrait du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Vérifie si le token est valide :
     * <ul>
     *     <li>L'email dans le token correspond à celui de l'utilisateur</li>
     *     <li>Le token n'est pas expiré</li>
     * </ul>
     *
     * @param token        JWT à valider.
     * @param userDetails  Détails de l'utilisateur attendu.
     * @return true si valide, false sinon.
     */
    public boolean isTokenValide(String token, UserDetails userDetails) {
        String extractedEmail = extractUsername(token);
        return (extractedEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * Vérifie si le token est expiré.
     *
     * @param token JWT à vérifier.
     * @return true si expiré, false sinon.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * Méthode utilitaire pour extraire une information (claim) du token.
     *
     * @param token          JWT à analyser.
     * @param claimsResolver Fonction qui récupère un champ spécifique de Claims.
     * @param <T>            Type de la donnée extraite.
     * @return Donnée extraite.
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
