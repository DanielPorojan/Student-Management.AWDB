#!/bin/sh

echo "⏳ Waiting for MySQL to be available at $SPRING_DATASOURCE_URL..."

while ! nc -z db 3306; do
  sleep 1
done

echo "✅ MySQL is available – starting Spring Boot app"
exec java -jar app.jar
