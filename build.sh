#!/bin/bash

echo "Compiling backend outside Docker..."
cd swapi-backend
if [ -f mvnw ]; then
  ./mvnw clean package -DskipTests
else
  mvn clean package -DskipTests
fi
cd ..

echo "Building frontend outside Docker..."
cd swapi-frontend
if [ ! -d node_modules ]; then
  echo "Installing dependencies..."
  npm install
fi
npm run build --configuration=production
if [ ! -d dist ]; then
  echo "ERROR: /dist folder not created"
  exit 1
fi
cd ..

echo "Stopping previous containers..."
docker compose down

echo "Starting containers..."
docker compose up --build
