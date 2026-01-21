# 📘 Cahier des charges
## Projet : Patient – Gestion des patients USLD

---

## 1. Présentation générale du projet

### 1.1 Contexte

Les établissements de soins de longue durée (USLD) prennent en charge des patients aux profils variés, nécessitant un suivi médical, organisationnel et administratif rigoureux.  
La gestion de ces informations est souvent fragmentée, ce qui complique l’accès rapide aux données essentielles pour les soignants.

Le projet **Patient** a pour objectif de proposer une **application web sécurisée** permettant de centraliser et structurer la gestion des patients, des soignants, des soins et des rendez-vous médicaux.

---

### 1.2 Objectifs du projet

- Centraliser les informations des patients
- Différencier les patients selon leur type médical
- Faciliter le suivi des soins et des rendez-vous
- Sécuriser l’accès aux données sensibles
- Mettre en place une architecture claire, évolutive et maintenable

---

## 2. Périmètre fonctionnel

### 2.1 Fonctionnalités incluses (Version 1)

#### Authentification et sécurité
- Connexion utilisateur via identifiant et mot de passe
- Génération et validation de token JWT
- Accès aux fonctionnalités réservé aux utilisateurs authentifiés

#### Gestion des patients
- Création d’un patient
- Modification des informations
- Suppression d’un patient
- Consultation du détail d’un patient
- Affichage de la liste des patients
- Filtrage et recherche des patients

#### Types de patients
- Patient USLD
- Patient Alzheimer
- Patient Sans Soin

#### Gestion des soignants
- Création, modification, suppression
- Association à plusieurs patients

#### Gestion des rendez-vous
- Création de rendez-vous
- Association à un patient et à un soignant
- Modification et suppression
- Consultation par patient

#### Gestion des soins
- Ajout de soins
- Modification et suppression
- Consultation des soins associés à un patient

---

### 2.2 Hors périmètre (évolutions futures)

- Statistiques avancées
- Tableaux de bord
- Notifications (email, SMS)
- Historique détaillé des modifications
- Gestion fine des permissions par spécialité médicale

---

## 3. Utilisateurs et rôles

### 3.1 Rôles définis

#### ADMIN
- Création des comptes utilisateurs
- Accès à la liste des soignants
- Supervision globale de l’application

#### USER
- Consultation et gestion des patients
- Gestion des soins et des rendez-vous

**Remarque :**  
L’inscription publique n’est pas autorisée. Les comptes sont créés par un administrateur afin de garantir la sécurité du système.

---

## 4. Spécifications fonctionnelles détaillées

### 4.1 Modélisation des patients

Les patients sont représentés par une **classe abstraite `Patient`**, dont héritent :

- `PatientUSLD`
- `PatientAlzheimer`
- `PatientSansSoin`

#### Choix technique – Héritage
Ce choix permet :
- une meilleure lisibilité du modèle
- une réduction de la duplication de code
- une extensibilité facilitée pour l’ajout de nouveaux types de patients

---

### 4.2 Recherche et filtrage

L’application permet :
- la recherche de patients par nom ou prénom
- le filtrage par type de patient
- l’affichage du service associé au patient

---

### 4.3 Gestion des rendez-vous

- Un rendez-vous est obligatoirement associé à un patient existant
- Il est également lié à un soignant
- Une date et une heure sont requises
- Les erreurs sont gérées via des codes HTTP adaptés

---

### 4.4 Gestion des soins

- Les soins sont associés à un patient
- Ils peuvent être ajoutés, modifiés ou supprimés
- Les soins permettent un suivi médical structuré

---

## 5. Architecture technique

### 5.1 Back-end

#### Technologies utilisées
- Java 21
- Spring Boot
- Spring Web (API REST)
- Spring Data JPA
- Spring Security + JWT
- MySQL / H2
- Swagger OpenAPI
- JUnit / Mockito

#### Architecture applicative
- Controller
- Service
- Repository
- DTO
- Mapper
- Entity
- Gestion centralisée des exceptions

---

### 5.2 Front-end (contexte du projet global)

- Angular 19
- TypeScript
- Services dédiés par type de patient
- Modèles typés
- Formulaires réactifs
- Service centralisé de gestion des erreurs

---

## 6. Sécurité

- Authentification basée sur JWT
- Protection des routes métiers
- Gestion des rôles `ADMIN` et `USER`
- Réponses HTTP normalisées (401, 403, 404)

---

## 7. Tests et qualité

- Tests unitaires sur les services
- Tests des cas :
    - création
    - mise à jour existante / inexistante
    - suppression
    - recherche
- Vérification des règles métier

---

## 8. Documentation API

- Documentation Swagger accessible
- Annotations OpenAPI sur les contrôleurs et DTO
- Codes de réponse standards :
    - 200 OK
    - 201 CREATED
    - 400 BAD REQUEST
    - 403 FORBIDDEN
    - 404 NOT FOUND

---

## 9. Livrables

- Backend Spring Boot fonctionnel
- API documentée via Swagger
- Tests unitaires
- README de présentation
- Cahier des charges détaillé

---

## 10. Conclusion

Le projet **Patient – Gestion des patients USLD** a été conçu comme une application professionnelle, mettant en œuvre :

- une architecture claire et maintenable
- des choix techniques justifiés
- une gestion sécurisée des données
- une logique métier cohérente

Ce projet a vocation à démontrer la capacité à concevoir et développer une application complète, proche des standards rencontrés en entreprise.
