#Imagen base o modelo para el contenedor de nuestro proyecto
FROM eclipse-temurin:17-jdk-alpine

#Informar en que puerto funcionara nuestro contenedor
EXPOSE 9000

#Definir el direcotrio raiz del contenedor
WORKDIR /root

#Copiar y pergar archivos al contenedor
COPY ./pom.xml /root
#Meter maven al contenedor
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#Descargar las dependencias necesarias para el funcionamiento del proyecto
RUN ./mvnw dependency:go-offline

#Copiar el codigo fuente del proyecto al contenedor
COPY ./src /root/src

#Construir la aplicacion
RUN ./mvnw clean install -DskipTests

#Levantar el proyecto cuando el contenedor inice
ENTRYPOINT [ "java","-jar","target/Autos-0.0.1-SNAPSHOT.jar" ]