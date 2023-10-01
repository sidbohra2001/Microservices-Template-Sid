FROM maven:3.9.4-eclipse-temurin-20-alpine as build
WORKDIR /Inventory-Management-System/Admin
COPY . .
RUN mvn clean compile package -Dmaven.test.skip

FROM eclipse-temurin
WORKDIR /Inventory-Management-System/Admin
COPY --from=build /Inventory-Management-System/Admin/target/Admin.jar ./target/Admin.jar
CMD ["java", "-jar", "target/Admin.jar"]