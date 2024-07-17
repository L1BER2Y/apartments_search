FROM eclipse-temurin:19-jre-focal

ADD ./target/user-Service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/user-Service.jar"]

EXPOSE 8080