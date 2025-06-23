package com.example.demo.Repository;

import com.example.demo.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
    public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    }

