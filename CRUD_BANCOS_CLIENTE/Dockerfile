# Usa una imagen base de OpenJDK para Java 11
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado de la aplicacion al contenedor
COPY target/CrudBancoCliente-1.0.jar app.jar

# Expone el puerto 8080 que es el que se utiliza el proyecto
EXPOSE 8080

# Comando para ejecutar tu aplicacion Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]

# Ejecutar en el directorio del proyecto donde se encuentre este archivo los siguientes comando en este orden:
# docker build -t crud-banco-cliente:1.0 . //para crear el docker
# docker start myApp //iniciar la app
# docker stop myApp //detener la app
# docker ps //ver el status general de los contenedores

