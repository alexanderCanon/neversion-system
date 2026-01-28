FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw package

EXPOSE 8080

CMD ["java", "-jar", "target/store-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]