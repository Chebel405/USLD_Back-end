package com.example.demo.Repository;

import com.example.demo.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
     Optional<Utilisateur> findByEmail(String email);

 }

