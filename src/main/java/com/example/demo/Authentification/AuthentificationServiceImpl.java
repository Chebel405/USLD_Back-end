package com.example.demo.Authentification;

import com.example.demo.Authentification.AuthentificationResponseDTO;
import com.example.demo.Authentification.LoginRequestDTO;
import com.example.demo.Authentification.RegisterRequestDTO;
import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthentificationServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthentificationResponseDTO register(RegisterRequestDTO request) {
        // Création d’un nouvel utilisateur avec mot de passe encodé
        Utilisateur utilisateur = Utilisateur.builder()
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .build();

        utilisateurRepository.save(utilisateur);

        // Simulation de la génération d’un JWT
        String token = "JWT_TOKEN_EN_ATTENTE";
        return new AuthentificationResponseDTO(token);

    }

    @Override
    public AuthentificationResponseDTO login(LoginRequestDTO request) {
        // Simulation pour l’instant
        String token = "JWT_TOKEN_LOGIN";
        return new AuthentificationResponseDTO(token);
    }
}
