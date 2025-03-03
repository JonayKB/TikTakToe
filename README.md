# TikTakToe
- [TikTakToe](#tiktaktoe)
  - [Description](#description)
  - [Usage](#usage)

## Description
  TikTakToe is a project that simulates a TicTacToe game using React Native for client, handling multiplayer and single player games.

  And the multiplayer games use a APi in Java, using Spring Boot.

  Also, it is possible to upload players profiles photos.

  All is secured using JWT tokens.

## Usage
- React Native:
```bash
    cd TikTakToeApp
    npm i
    npm start
```

- Api:
  - Setup env variables:
 ```bash
 export EMAIL_USER="your smtp address"
 export EMAIL_PASSWORD="your smtp token"
 export SECRET_HASH="your secret hash"
 export PROFILE="dev"
 ```
 - Install a mysql server
   - It need a user called "jonaykb" with password "1q2w3e4r" 
 - Prepare Project:
  ```bash
  mvn clean install test
  ```
  - Run Project:
  ```bash
  mvn spring-boot:run
  ```


