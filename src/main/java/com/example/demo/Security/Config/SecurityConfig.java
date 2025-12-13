package com.example.demo.Security.Config;

import com.example.demo.Security.Jwt.JwtAuthentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration principale de Spring Security.
 * <p>
 * Cette classe :
 * - Définit les règles de sécurité pour les routes HTTP
 * - Configure le filtre JWT pour intercepter les requêtes
 * - Configure la gestion de l’authentification (DAO, encoder, etc.)
 */
@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthentificationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthentificationFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configure les règles de sécurité HTTP :
     * - Désactive CSRF (inutile pour une API REST stateless)
     * - Autorise l’accès libre à l’authentification et à la documentation Swagger
     * - Protège toutes les autres routes avec authentification
     * - Utilise un filtre JWT + une session stateless
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable()) // désactive CSRF pour les API REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/register",
                                "/auth/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/patients/alzheimer/**",
                                "/patients/sanssoin/**",
                                "/patients/usld/**",
                                "/patients/tous"
                        ).permitAll() // accès libre aux routes Swagger + login/register
                        .anyRequest().authenticated() // le reste est sécurisé

                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // pas de session
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Définit la stratégie d’authentification utilisée (DAO basé sur UserDetailsService + passwordEncoder).
     *
     * @return AuthenticationProvider prêt à l’emploi.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Encodeur de mot de passe BCrypt, robuste et recommandé.
     *
     * @return Encodeur BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // encodage des mots de passe
    }

    /**
     * Fournit un gestionnaire d’authentification utilisé pour la connexion manuelle (/login).
     *
     * @param config Configuration Spring
     * @return AuthenticationManager
     * @throws Exception en cas d’erreur
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // pour l’authentification manuelle (login)
    }
}
