package com.example.demo.Security.Jwt;

import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service responsable de la génération et de la validation des tokens JWT.
 */
@Service
public class JwtService {

    /**
     * Clé secrète JWT lue depuis application.properties
     * ⚠️ DOIT faire au moins 32 caractères pour HS256
     */
    @Value("${jwt.secret}")
    private String secretKey;

    private final UtilisateurRepository utilisateurRepository;

    public JwtService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Génère la clé de signature HMAC à partir du secret
     */
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Génère un token JWT basé sur l'email de l'utilisateur.
     */
    public String generateToken(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", utilisateur.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrait le nom d'utilisateur (email) du token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait tous les claims du token.
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Vérifie si le token est valide.
     */
    public boolean isTokenValide(String token, UserDetails userDetails) {
        String extractedEmail = extractUsername(token);
        return extractedEmail.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Vérifie si le token est expiré.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * Méthode utilitaire pour extraire une info du token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
