# Dockerfile produccion

FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app

# Copiar todo el proyecto
COPY . .

#(sin dependency:go-offline que está causando problemas la poronga esta)
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
