# If JAR not uploaded

FROM maven:3.9.4-eclipse-temurin-20-alpine as build
WORKDIR /<directory>
COPY . .
RUN mvn clean compile package -Dmaven.test.skip

FROM eclipse-temurin
WORKDIR /<directory>
COPY --from=build /<directory>/target/<application>.jar ./target/<application>.jar
CMD ["java", "-jar", "target/<appliation>.jar"]

# If JAR uploaded

FROM wonderd/jre-alpine
WORKDIR /<directory>
COPY  /<directory>/target/<application>.jar ./target/<application>.jar
CMD ["java", "-jar", "target/<appliation>.jar"]