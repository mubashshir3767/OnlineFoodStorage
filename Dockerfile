#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ./target/OnlineFoodStorage-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#
#FROM postgres:latest
#
#ENV POSTGRES_DB=OnlineFoodStorage
#ENV POSTGRES_USER=postgres
#ENV POSTGRES_PASSWORD=1
#
#FROM redis:latest
