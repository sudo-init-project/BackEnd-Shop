#Esto va a ser el dockerfile de produccion

FROM maven:3.9-openjdk-17-slim AS builder
WORKDIR /app

# Copia archivos de configuración Maven primero (para cache)
COPY pom.xml .
COPY .mvn/ .mvn/
RUN mvn dependency:go-offline -B

# Copia código fuente y compila
COPY src ./src
RUN mvn clean package -DskipTests -B
FROM openjdk:17-jdk-slim
WORKDIR /app

# Crear usuario no-root
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
# Copiar solo el JAR compilado
COPY --from=builder /app/target/*.jar app.jar
# Variables de entorno
ENV JAVA_OPTS="-Xmx512m -Xms256m"
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
