🏥 USLD_Back-end – Gestion des Patients, Soignants, Soins et Rendez-vous

Ce dépôt contient le backend de l’application **Patient – Gestion des patients USLD**, développée avec **Spring Boot 3**.

L’application fournit une **API REST sécurisée** permettant de gérer :
- les patients (USLD, Alzheimer, Sans soin)
- les soignants
- les soins
- les rendez-vous médicaux

---

## 🎯 Objectifs du projet

- Centraliser la gestion des patients en USLD
- Différencier les patients selon leur type médical
- Garantir la sécurité des accès aux données
- Proposer une architecture claire, évolutive et testée

---

## 📋 Fonctionnalités principales

### 👤 Gestion des patients
- Création, modification, suppression
- Consultation détaillée
- Séparation par type :
  - PatientUSLD
  - PatientAlzheimer
  - PatientSansSoin
- Recherche et filtrage (nom, prénom, type)

### 👨‍⚕️ Gestion des soignants
- Association à plusieurs patients
- Gestion CRUD complète

### 📆 Rendez-vous
- Création et association à un patient et un soignant
- Consultation et suppression

### 💊 Soins
- Ajout et suivi des soins
- Association aux patients

---

## 🧱 Architecture ET choix techniques

- Architecture en couches :
  - Controller
  - Service
  - Repository
  - DTO
  - Mapper
  - Entity
- Héritage JPA avec une classe abstraite `Patient`
- Mapping DTO / Entity pour maîtriser les données exposées
- Gestion centralisée des erreurs
- Sécurité via **JWT**
- Rôles : `ADMIN`, `USER`

📘 Documentation fonctionnelle complète :  
➡️ `docs/CAHIER_DES_CHARGES_PATIENT.md`

---

## 🚀 Technologies utilisées

- Java 21
- Spring Boot 3.4.5
- Maven
- Spring Web (REST API)
- Spring Data JPA
- Spring Security + JWT
- H2 ou MySQL (selon configuration)
- Lombok (facultatif)
- Swagger / OpenAPI 3
- JUnit 5 & Mockito

---

## ⚙️ Prérequis

- Java JDK 21
- Maven 3+
- IDE (IntelliJ, VS Code…)

---

## ▶️ Lancer l’application

```bash
./mvnw spring-boot:run
