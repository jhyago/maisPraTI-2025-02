FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn -B -q -e -DskipTests dependecy:go-offline

COPY src ./src
RUN mvn -B -DskipTests clean package

FROM eclipse-termurin:21-jre
WORKDIR /app

COPY --from:build /app/target/*.jar /app/app.jar/

ENV JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="default"

ENTRYPOINT ["/bin/sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar"]

EXPOSE 8080