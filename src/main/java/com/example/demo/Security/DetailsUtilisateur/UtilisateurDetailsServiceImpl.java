package com.example.demo.Security.DetailsUtilisateur;

import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implémentation de UserDetailsService.
 * Utilisée par Spring Security pour charger un utilisateur à partir de l’email.
 */
@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouvé avec l’email : " + email));

        // Tu peux ici retourner un objet personnalisé, mais au plus simple :
        return org.springframework.security.core.userdetails.User
                .withUsername(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .authorities("UTILISATEUR") // pour l’instant, un rôle simple
                .build();
    }
}
