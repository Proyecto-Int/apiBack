# Usa la imagen de JDK de OpenJDK como base
FROM openjdk:17-jdk-alpine

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY build/libs/*.jar app.jar

# Expone el puerto en el que escucha tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
