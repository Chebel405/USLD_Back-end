package com.example.demo.Authentification;

public interface AuthentificationService {

    /**
     * Enregistre un nouvel utilisateur et retourne un token JWT.
     */
    AuthentificationResponseDTO register(RegisterRequestDTO request);

    /**
     * Authentifie un utilisateur existant et retourne un token JWT.
     */
    AuthentificationResponseDTO login(LoginRequestDTO request);
}
