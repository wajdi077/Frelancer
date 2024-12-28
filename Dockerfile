# Étape 1: Utiliser l'image de base pour Gradle
FROM gradle:8.5-jdk17 AS build

# Étape 2: Définir le répertoire de travail et préparer le cache Gradle
WORKDIR /app
RUN mkdir -p /home/gradle/.gradle && chown gradle:gradle /home/gradle/.gradle

# Étape 3: Copier les fichiers dans l'image Docker
COPY . .

# Étape 4: Construire le projet Gradle
RUN gradle build --no-daemon
# Étape 5: Utiliser l'image de base pour exécuter l'application
FROM openjdk:17-jdk-slim

# Étape 6: Définir le répertoire de travail et copier l'application
WORKDIR /app
COPY --from=build /app/build/libs/wma-0.0.1-SNAPSHOT.jar app.jar

# Étape 7: Exposer le port de l'application
EXPOSE 8080

# Étape 8: Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
