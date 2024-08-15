# Usa una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR de la aplicación en la imagen
COPY build/libs/empat-api-0.0.1-SNAPSHOT.jar /app/empat-api-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que tu aplicación escucha
EXPOSE 8080

# Define el comando que se ejecutará al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "empat-api-0.0.1-SNAPSHOT.jar"]
