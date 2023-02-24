# syntax=docker/dockerfile:1

FROM openjdk:8-jdk-alpine

COPY ./target/demo-1.0.jar /demo.jar

ENTRYPOINT ["java","-jar","/demo.jar"]