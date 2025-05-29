# 🏥 Gestion des Soignants et relation avec les Patients - Application Spring Boot

Cette application back-end permet la gestion de différents types de patients dans un établissement médical. Elle est développée avec **Spring Boot**, en suivant une architecture claire et modulaire.
Cette application gère également les **soignants** (médecins, infirmiers, etc.) pour permettre une prise en charge coordonnée des patients.

---

## 📚 Objectif du projet

Mettre en place une API REST pour :

- Gestion des informations des patients.
- Gestion des informations des soignants.
- Distinguer les types de patients (USLD, Alzheimer, sans soins)
- Associer des attributs spécifiques selon leur besoin en soins
- Association de plusieurs soignants à un patient via une relation bidirectionnelle.
- Chaque patient peut avoir plusieurs soignants référents.
- Chaque soignant peut avoir plusieurs patients à sa charge.
- Utilisation de DTOs (Data Transfer Objects) pour gérer la sérialisation des entités et éviter les boucles infinies lors des conversions JSON.
- Les mappers `PatientMapper` et `SoignantMapper` convertissent les entités en DTOs et inversement, incluant la gestion des listes d’IDs et des objets liés.
- La relation est persistée grâce à Spring Data JPA, garantissant la cohérence des données en base.


---

### Exemple d’appel API pour créer un patient avec un soignant associé

```bash
curl -X POST http://localhost:8080/api/patients \
 -H "Content-Type: application/json" \
 -d '{
    "nom": "Dama",
    "prenom": "Albert",
    "dateNaissance": "1956-04-30",
    "type": "AVEC_SOINS",
    "numeroChambre": 414,
    "niveauAutonomie": "Faible",
    "toiletteAssistee": true,
    "aideHabillage": true,
    "aideRepas": true,
    "soignantsIds": [1]
}'

---

## 🚀 Technologies utilisées

- Java 21
- Spring Boot 3.4.5
- Maven
- Spring Web
- Spring Data JPA
- H2 ou MySQL (selon config)
- Lombok (facultatif)
- Swagger (optionnel)



## ⚙️ Prérequis

- Java JDK 21
- Maven 3+
- IDE comme IntelliJ ou VS Code

---

## ▶️ Lancer l'application

```bash
./mvnw spring-boot:run


