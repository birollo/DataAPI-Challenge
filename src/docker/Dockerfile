FROM remote-docker.artifactory.swisscom.com/openjdk:11-jdk-slim
COPY libs/DataAPI-Challenge.jar DataAPI-Challenge.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/DataAPI-Challenge.jar"]
