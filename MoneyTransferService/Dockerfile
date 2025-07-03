FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY target/MoneyTransferService-0.0.1-SNAPSHOT.jar app.jar
COPY ./moneytransferservice.log ./moneytransferservice.log

CMD ["java", "-jar","app.jar"]


