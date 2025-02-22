FROM maven:3.8.5-openjdk-17 AS build
copy . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/demo3-0.0.1-SNAPSHOT.jar demo3.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo3.jar"]