package com.example.demo.Mapper;

import com.example.demo.Dto.UtilisateurDTO;
import com.example.demo.Entity.Utilisateur;
import com.example.demo.Repository.SoignantRepository;



public class UtilisateurMapper {

    /**
     * Convertit un objet Utilisateur (Entity) en UtilisateurDTO
     * @param utilisateur l'entité Utilisateur à convertir
     * @return l'objet UtilisateurDTO correspondant
     */

    public static UtilisateurDTO toDTO(Utilisateur utilisateur){
        if(utilisateur == null) return null;

        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setEmail(utilisateur.getEmail());
       /* dto.setMotDePasse(utilisateur.getMotDePasse());*/
        return dto;
    }

    /**
     * Convertit un UtilisateurDTO en Utilisateur (Entity).
     *
     * @param dto le DTO à convertir
     */
    public static Utilisateur toEntity(UtilisateurDTO dto){
        if(dto == null) return null;

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setEmail(dto.getEmail());
     /*   utilisateur.setMotDePasse(dto.getMotDePasse());*/
        return utilisateur;
    }
}
