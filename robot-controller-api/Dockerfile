FROM eclipse-temurin:17.0.10_7-jre-focal

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]