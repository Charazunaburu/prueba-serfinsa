# Etapa 1: Construcción (Build)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de configuración de Gradle para aprovechar el cache de capas
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Descargar dependencias (esto evita descargarlas cada vez que cambies el código)
RUN ./gradlew dependencies --no-daemon

# Copiar el código fuente y construir el JAR
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear un usuario sin privilegios para mayor seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR generado (Gradle lo guarda en build/libs)
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto que configuraste en tu application.properties
EXPOSE 9090

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]