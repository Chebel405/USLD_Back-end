package com.example.demo.Authentification;

import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import com.example.demo.Security.Jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthentificationServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthentificationResponseDTO register(RegisterRequestDTO request) {
        // Création d’un nouvel utilisateur avec mot de passe encodé
        Utilisateur utilisateur = Utilisateur.builder()
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .build();

        utilisateurRepository.save(utilisateur);

        String token = jwtService.generateToken(utilisateur.getEmail());

        return new AuthentificationResponseDTO(token);

    }

    @Override
    public AuthentificationResponseDTO login(LoginRequestDTO request) {

        String token = jwtService.generateToken(request.getEmail());

        return new AuthentificationResponseDTO(token);
    }
}
