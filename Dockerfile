FROM maven:3.9.0-eclipse-temurin:17 as builder
WORKDIR /app
COPY ./ ./
RUN mvn dependency:go-offline
RUN mvn clean install

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar" ]