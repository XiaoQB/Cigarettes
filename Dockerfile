FROM openjdk:11

COPY /target/Cigarettes-1.0-SNAPSHOT.jar /app/Cigarettes.jar
WORKDIR /app
EXPOSE 8888
ENTRYPOINT ["sh","-c","java  -jar Cigarettes.jar"]