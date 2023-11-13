FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run

# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src src
# RUN mvn clean package -DskipTests

# FROM openjdk:17
# WORKDIR /app
# VOLUME /app
# COPY --from=build /app/target/*.jar app.jar
# ENV JAVA_TOOL_OPTIONS=-Dspring.devtools.restart.enabled=true
# CMD ["java", "-jar", "app.jar"]
