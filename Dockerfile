# Utilise l'image Docker officielle Maven comme image de base
FROM maven:3.9.9-amazoncorretto-21 AS build

# Set le répertoire de travail à /app
WORKDIR /app

# Copie le pom.xml et les sources dans le répertoire de travail
COPY pom.xml .
COPY src ./src
COPY checkstyle ./checkstyle

# Construit l'application avec Maven
RUN mvn clean package -DskipTests

# --- Etape 2 : Exécution du JAR dans un conteneur plus léger ---
FROM amazoncorretto:21-al2023-headless

# Install shadow-utils to get usermod
# Create user
# Remove shadow-utils
RUN yum update -y \
    && yum install -y shadow-utils  \
    && groupadd spring && useradd -m -g spring spring \
    && yum remove -y shadow-utils \
    && yum clean all

# Changer de l'utilisateur 'root' à l'utilisateur 'spring'
USER spring:spring

# Copie le JAR du conteneur de build vers le conteneur de deploying
COPY --chown=spring --chmod=700 --from=build /app/target/*.jar /app/app.jar

# Exécute l'application Java
CMD ["java", "-jar", "/app/app.jar"]

# Ajoute un healthcheck pour vérifier que l'application s'exécute correctement
HEALTHCHECK --interval=1m --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
