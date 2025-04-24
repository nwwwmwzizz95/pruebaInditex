# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /workspace
COPY inditex-domain inditex-domain
COPY inditex-persistence inditex-persistence
COPY inditex-app inditex-app
COPY pom.xml .

RUN mvn clean package -ntp

# Etapa de ejecución
FROM eclipse-temurin:17-jre-centos7

WORKDIR /

# Copia solo el .jar generado en el build
COPY --from=build /workspace/inditex-app/target/inditex-app-0.1.0.jar /app.jar

ENV VERSION=0.1.0
ENV JAVA_OPTS=-Dspring.profiles.active=production

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=production /app.jar"]
