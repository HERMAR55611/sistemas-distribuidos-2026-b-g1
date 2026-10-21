@echo off
cd /d "%~dp0reservation-service"
echo Running reservation-service tests with Docker (first time downloads dependencies)...
docker run --rm -v "%cd%":/app -v futbolix-m2:/root/.m2 -w /app maven:3.9-eclipse-temurin-21 mvn -B test
pause
