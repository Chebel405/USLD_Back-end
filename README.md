# 🏥 USLD_Back-end – Gestion des Soignants, Patients, Soins et Rendez-vous

Ce dépôt contient le **backend** de l'application *USLD* (*Unité de Soins de Longue Durée*), développée avec **Spring Boot 3**.  
Il fournit une API REST pour gérer les patients, les soignants, les soins et les rendez-vous médicaux.

---

## 🎯 Objectif du projet

- 📋 Gestion des informations des **patients** (type USLD, Alzheimer, sans soins)
- 👨‍⚕️ Gestion des **soignants** (association à plusieurs patients)
- 📆 Gestion des **rendez-vous médicaux**
- 💊 Suivi des **soins effectués**
- 🔄 Association entre entités (relation bidirectionnelle)
- 📦 Utilisation de **DTOs** pour contrôler les données exposées
- 🔁 Mapping entité/DTO avec gestion des listes et objets liés
- 💾 Persistance via **Spring Data JPA** avec gestion de la cohérence

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
- Spring Web (REST API)
- Spring Data JPA (persistances)
- H2 ou MySQL (selon configuration)
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
