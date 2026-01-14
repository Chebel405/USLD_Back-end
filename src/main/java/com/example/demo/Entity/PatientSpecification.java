package com.example.demo.Entity;

import org.springframework.data.jpa.domain.Specification;

/**
 * PatientSpecification
 *
 * Cette classe permet de construire des filtres de recherche dynamiques
 * pour l'entité Patient (et ses sous-classes : USLD, Alzheimer, SansSoin).
 *
 * Règle importante :
 * - Si un champ n'est pas renseigné → on retourne null
 * - Spring ignore automatiquement les Specifications null
 * - Cela évite que la requête renvoie tous les patients
 */
public class PatientSpecification {

    /**
     * Recherche partielle (LIKE) insensible à la casse sur un champ String
     */
    public static Specification<Patient> contientIgnoreCase(String champ, String valeur) {

        // 🔴 CHANGEMENT ICI
        if (valeur == null || valeur.isBlank()) {
            return null; // pas de filtre
        }

        String v = valeur.toLowerCase();

        return (root, query, cb) ->
                cb.like(cb.lower(root.get(champ)), "%" + v + "%");
    }

    /**
     * Recherche par égalité exacte (dates, enums, etc.)
     */
    public static Specification<Patient> egal(String champ, Object valeur) {

        // 🔴 CHANGEMENT ICI
        if (valeur == null) {
            return null; // pas de filtre
        }

        return (root, query, cb) ->
                cb.equal(root.get(champ), valeur);
    }

    /**
     * Filtre selon le type concret de Patient
     * (USLD / Alzheimer / SansSoin)
     */
    public static Specification<Patient> typePatient(String typePatient) {

        // 🔴 CHANGEMENT ICI
        if (typePatient == null || typePatient.isBlank()) {
            return null; // pas de filtre
        }

        return switch (typePatient.toUpperCase()) {
            case "USLD" ->
                    (root, query, cb) -> cb.equal(root.type(), PatientUSLD.class);
            case "ALZHEIMER" ->
                    (root, query, cb) -> cb.equal(root.type(), PatientAlzheimer.class);
            case "SANSSOIN", "SANS_SOIN" ->
                    (root, query, cb) -> cb.equal(root.type(), PatientSansSoin.class);
            default -> null;
        };
    }
}
