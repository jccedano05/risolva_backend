FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/risolva-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]