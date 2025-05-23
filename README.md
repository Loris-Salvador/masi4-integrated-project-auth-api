# Architecture

## Clean Architecture

![](data:image/jpeg;base64,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)

### Présentation

C'est ici que les requêtes arrivent. Ce sont les endpoints qui sont exposés à l'extérieur. On y retrouve donc tous les types de contrôleurs ou de handlers.  
Cette couche est responsable de recevoir les entrées utilisateur (HTTP, CLI, etc.) et de les transmettre à la couche Application.

### Application

C'est ici que se trouve la logique métier applicative. Cette couche **ne connaît pas** la couche Présentation, mais **l'inverse est vrai** :  
les contrôleurs vont appeler les cas d'utilisation (use cases) de cette couche.  
La couche Présentation ne contient donc **aucun détail d'implémentation** métier.

### Domaine

Le domaine peut être fusionné avec la couche Application dans certains projets.  
Lorsqu’il est séparé, c’est ici qu’on place la logique métier pure — tout ce qui change très peu et représente le cœur de l’application  
(par exemple, les modèles et les exceptions métiers).  
C’est la partie la plus stable du code.

### Infrastructure

C’est la couche responsable des détails techniques et de la communication avec l’extérieur  
(bases de données, autres API, fichiers, etc.).  
Elle implémente les interfaces définies dans l’Application ou le Domaine, mais elle ne contient aucune logique métier.


# Installation

## Dépendances

Le projet demande deux services qui doivent être lancé manuellement (à défaut de ne pas avoir d'environnement de dev) :

- Une DB mongo
- un cache Redis

Le plus simple est de faire tourner les deux services sur docker avec port forwarding pour pouvoir accéder aux services en `localhost`

### Mongo

```bash
docker run -d --name mongodb-auth-api -p 27017:27017 -v mongo_data:/data/db mongo:8.0.5
```

### Redis


```bash
docker run --name redis -p 6379:6379 -d redis
```

**Voilà les deux dépendances sont désormais opérationnelles :)**

## Variables d'environnement

Il manque une dernière étape avant de pouvoir exécuter le projet : les variables d'environnement
(voir `src/main/resources/application.yaml`)

Il est possible d'override les valeurs pour les variables suivantes, mais la valeur par défaut ne devrait pas poser problèmes si mise en place standard :

- `MONGO_URI` : URI de connexion mongo
- `REDIS_URL` : URI de connexion Redis

Les variables suivantes doivent être définies avant de lancer le projet :


- `MAIL_USERNAME` : mail qui sert à envoyer le mail vers les users (pour tester cela peut être votre mail personnel)
- `MAIL_PASSWORD` : mot de passe d'application à créer sur votre compte Google → me demander (Loris) si problème pour le faire

- `TWILIO_ACCOUNT_SSID` : à retrouver dans le profil Twilio
- `TWILIO_AUTH_TOKEN` : à retrouver dans le profil Twilio
- `TWILIO_PHONE_NUMBER` : à retrouver dans le profil Twilio

- `TOKEN_SECRET_KEY` : clé pour la génération du token standard a générer totalement aléatoirement (attention taille minimum possible)
- `REFRESH_TOKEN_SECRET_KEY` : clé pour la génération du refresh token a générer totalement aléatoirement (attention taille minimum possible)

### Ajouter les variables d'environnement au projet

#### Ajouter un `.env` au projet

Je recommande de faire un `.env` à la racine du projet.  
Il devrait ressembler à cela :

```
MAIL_USERNAME=fakeMail
MAIL_PASSWORD=fakePassword

TWILIO_ACCOUNT_SSID=fakeSSID
TWILIO_AUTH_TOKEN=fakeToken
TWILIO_PHONE_NUMBER=fakeNumber

TOKEN_SECRET_KEY=fakeKey
REFRESH_TOKEN_SECRET_KEY=fakeKey
```


Je recommande fortement **Intellij** (ou un IDE qui gère les `.env`) :

1. edit configuration (juste à côté de run)
2. modify options
3. environnement variable
4. référencé le `.env` créé précédemment

#### Autre option

Passer les variables d'environnement lors du lancement de du projet en ligne de commande (voir ci-dessous) 

## Démarrer le projet

### Intellij :

Run normalement avec donc le `.env` référencé

### Ligne de commande

*Optionnel : ajouter les variables d'environnement maintenant si pas fait avec `.env`*

#### Windows

```bash
./mvnw.cmd spring-boot:run
```

#### Linux/MacOs

```bash
./mvn spring-boot:run
```
