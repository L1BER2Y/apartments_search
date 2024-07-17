FROM eclipse-temurin:19-jre-focal

ADD ./target/audit-Service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/audit-Service.jar"]

EXPOSE 8084