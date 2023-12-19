# ChâTop

![Spring Boot Logo](https://www.vectorlogo.zone/logos/springio/springio-icon.svg)

## Description
ChâTop est une API backend pour l'application Front-End disponible à cette adresse : https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.

Elle permet aux utilisateurs de s'authentifier, d'envoyer des messages, de gérer des locations.

## Fonctionnalités
- **Authentification des Utilisateurs** : Permet l'inscription et la connexion des utilisateurs.
- **Gestion des Messages** : Envoie et reçoit des messages entre utilisateurs par rapport à une location.
- **Gestion des Locations** : Permet la création, la consultation et la modification des informations de location.

## Technologies Utilisées 🛠️
- Spring Boot
- JPA / Hibernate
- MySQL
- JWT pour l'authentification
- Cloudinary
- Dotenv

## Installation 🔌

Pour installer et exécuter le projet localement, suivez ces étapes :

1. **Cloner le Dépôt** :
   ```bash
   git clone https://github.com/votreusername/ChatOpBackend.git
2. **Installer les dépendances** :
    ```bash
    npm install
    ```
3. **Démarrer le serveur**

## Installation de la Base de Données MySQL
Pour configurer et utiliser une base de données MySQL pour ce projet, suivez ces étapes :

### 1. **Connexion à MySQL** :

   Ouvrez votre terminal et connectez-vous à votre instance MySQL :

```bash
mysql -u root -p
```

Remplacez root par votre nom d'utilisateur MySQL si nécessaire. Saisissez votre mot de passe lorsque vous y êtes invité.

2. ### **Création de la BDD** :

```bash
CREATE DATABASE NomDeVotreBaseDeDonnees;
```

3. ### **Configuration de l'application** :

Ajoutez les configurations nécessaires dans votre fichier .env pour la connexion à la base de données. Par exemple :

```agsl
DATABASE_URL=jdbc:mysql://localhost/NomDeVotreBaseDeDonnees
DATABASE_USERNAME=NomUtilisateur
DATABASE_PASSWORD=MotDePasse
```

## Configurer les Variables d'Environnement

Créez un fichier .env à la racine du projet et ajoutez les configurations nécessaires pour la base de données et Cloudinary.
Les noms de variable du .env sont spécifiés dans la classe **EnvInitializer**


## ➤ API Reference

Vous pouvez accéder à la documentation des routes à cette adresse une fois le serveur lancé : http://localhost:3001/swagger-ui.html




        