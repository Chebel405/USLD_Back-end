package com.example.demo.Security.DetailsUtilisateur;

import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implémentation personnalisée de l'interface UserDetailsService.
 * <p>
 * Elle est utilisée automatiquement par Spring Security lors de l'authentification.
 * Le framework lui passe l'email (ou login) de l'utilisateur, et cette classe
 * est chargée de retrouver cet utilisateur en base.
 */
@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    /**
     * Constructeur injectant le repository pour accéder aux utilisateurs.
     */
    public UtilisateurDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Charge un utilisateur depuis la base de données à partir de son email.
     *
     * @param email l'identifiant (utilisé ici comme nom d'utilisateur)
     * @return un objet UserDetails contenant les informations de l'utilisateur
     * @throws UsernameNotFoundException si l'utilisateur n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Recherche en base de l'utilisateur par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouvé avec l’email : " + email));

        // Convertit l'entité Utilisateur vers un objet UserDetails attendu par Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(utilisateur.getEmail()) // identifiant
                .password(utilisateur.getMotDePasse()) // mot de passe (déjà encodé)
                .authorities("UTILISATEUR") // pour l’instant, un rôle simple
                .build();
    }
}
