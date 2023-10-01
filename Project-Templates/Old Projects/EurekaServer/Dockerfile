FROM maven:3.9.4-eclipse-temurin-20-alpine as build
WORKDIR /Inventory-Management-System/EurekaServer
COPY . .
RUN mvn clean compile package -Dmaven.test.skip

FROM eclipse-temurin
WORKDIR /Inventory-Management-System/EurekaServer
COPY --from=build /Inventory-Management-System/EurekaServer/target/Eureka.jar ./target/Eureka.jar
CMD ["java", "-jar", "target/Eureka.jar"]