# 🏥 Gestion des Soignants, Patients et Rendez-vous - Application Spring Boot

Cette application back-end permet la gestion de différents types de patients dans un établissement médical, en incluant la planification des **rendez-vous médicaux** entre les patients et les soignants. Elle est développée avec **Spring Boot**, en suivant une architecture claire et modulaire.

---

## 📚 Objectif du projet

Mettre en place une API REST pour :

- Gestion des informations des **patients**
- Gestion des informations des **soignants**
- Gestion des **rendez-vous** entre patients et soignants
- Gestion des **soins effectués** dans le cadre des rendez-vous
- Distinguer les types de patients (USLD, Alzheimer, sans soins)
- Associer des attributs spécifiques selon leur besoin en soins
- Association de plusieurs soignants à un patient via une relation bidirectionnelle
- Utilisation de DTOs (Data Transfer Objects) pour gérer la sérialisation des entités et éviter les boucles infinies lors des conversions JSON
- Les mappers convertissent les entités en DTOs et inversement, incluant la gestion des listes d’IDs et des objets liés
- La relation est persistée grâce à Spring Data JPA, garantissant la cohérence des données en base

---

## 🗓️ Exemple d’appel API pour créer un rendez-vous

```bash
curl -X POST http://localhost:8081/rendezvous \
 -H "Content-Type: application/json" \
 -d '{
    "dateHeure": "2025-07-01T10:30:00",
    "motif": "Consultation ORL",
    "patientId": 1,
    "soignantId": 2,
    "soinId": 1
}'
```

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

---

## ⚙️ Prérequis

- Java JDK 21
- Maven 3+
- IDE comme IntelliJ ou VS Code

---

## ▶️ Lancer l'application

```bash
./mvnw spring-boot:run
```
