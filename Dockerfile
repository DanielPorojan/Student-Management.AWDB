FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/*.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh

RUN chmod +x wait-for-mysql.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for-mysql.sh"]
