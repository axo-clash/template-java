# --- Etape 1 : Build de l'application ---
FROM maven:3-eclipse-temurin-25-alpine AS build

WORKDIR /app

# Copie le pom.xml et les sources dans le répertoire de travail
COPY pom.xml ./
COPY src ./src
COPY checkstyle ./checkstyle

# Construit l'application avec Maven
RUN mvn clean package -DskipTests

# Determine les modules JDK reels utilises par l'application (jdeps)
# puis genere un runtime Java minimal ne contenant que ces modules (jlink)
RUN "$JAVA_HOME/bin/jar" xf /app/target/*.jar \
    && "$JAVA_HOME/bin/jdeps" --ignore-missing-deps -q --recursive --multi-release 25 \
        --print-module-deps --class-path 'BOOT-INF/lib/*' /app/target/*.jar > /app/deps.info \
    && "$JAVA_HOME/bin/jlink" --add-modules "$(cat /app/deps.info),jdk.crypto.ec" \
        --strip-debug --compress 'zip-9' --no-header-files --no-man-pages \
        --output /app/custom-jre

# Extrait le jar en couches : jar fin + librairies a plat, demarrage plus rapide
RUN "$JAVA_HOME/bin/java" -Djarmode=tools -jar /app/target/*.jar extract --layers --destination /app/extracted \
    && mv /app/extracted/application/*.jar /app/extracted/application/app.jar

# --- Etape 2 : Runtime Alpine minimal avec le JRE custom ---
FROM alpine:3.24.1

WORKDIR /app

ENV JAVA_HOME=/opt/jre
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV JAVA_OPTS="-XX:MaxRAMPercentage=90 -XX:MaxMetaspaceSize=256m --add-opens java.base/java.nio=ALL-UNNAMED -Dspring.aot.enabled=true"

# Cree un utilisateur non-root pour executer l'application
RUN addgroup -S spring && adduser -S -G spring spring

# Copie le runtime Java minimal et les couches de l'application
COPY --from=build /app/custom-jre $JAVA_HOME
COPY --chown=spring:spring --from=build /app/extracted/dependencies/ ./
COPY --chown=spring:spring --from=build /app/extracted/spring-boot-loader/ ./
COPY --chown=spring:spring --from=build /app/extracted/snapshot-dependencies/ ./
COPY --chown=spring:spring --from=build /app/extracted/application/ ./

USER spring:spring

# Port d'ecoute par defaut (surchargeable via la variable d'environnement PORT)
ENV PORT=9000
EXPOSE 9000

# Execute l'application Java
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]
