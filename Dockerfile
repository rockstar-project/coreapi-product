FROM openjdk:8-jdk-alpine
MAINTAINER Rockstar Team

VOLUME /tmp
COPY target/coreapi-product-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]