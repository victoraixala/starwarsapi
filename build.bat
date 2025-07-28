@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

echo Compiling backend outside Docker...
cd swapi-backend
if exist mvnw.cmd (
    call mvnw.cmd clean package -DskipTests
) else (
    call mvn clean package -DskipTests
)
cd ..

echo Building frontend outside Docker...
cd swapi-frontend
if not exist node_modules (
    echo Installing dependencies...
    call npm install
)
call npm run build --configuration=production
if not exist dist (
    echo ERROR: /dist folder not created
    exit /b 1
)
cd ..

echo Stopping previous containers...
docker compose down

echo Starting containers...
docker compose up --build
