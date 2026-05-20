FROM eclipse-temurin:21-jdk
COPY target/*.jar app.jar
ENV TZ=America/Mexico_City
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]