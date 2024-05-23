FROM openjdk:17-slim as builder

WORKDIR /app/cliente

COPY ./Cliente-NeorisTest/.mvn ./.mvn
COPY ./Cliente-NeorisTest/mvnw .
COPY ./Cliente-NeorisTest/pom.xml .

RUN ./mvnw clean package -DskipTests -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

COPY ./Cliente-NeorisTest/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/cliente/target/Cliente-NeorisTest-0.0.1-SNAPSHOT.jar .

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "Cliente-NeorisTest-0.0.1-SNAPSHOT.jar"]