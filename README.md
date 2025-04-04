# Tester l'api en local

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
