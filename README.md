# SWAPI Fullstack Project

This project is a fullstack application built with Spring Boot (backend) and Angular (frontend), containerized with Docker.

## Prerequisites

Before running the project, make sure you have:

- [Docker](https://www.docker.com)
- [Node.js & npm](https://nodejs.org) (only required if running the frontend manually)
- [Java](https://www.oracle.com/java/) (only required if not using Docker)

## Build Scripts

### 🔹 Windows

Run the script by double-clicking or via terminal:

```bat
build.bat
```

### 🔹 Linux/macOS

Ensure the script has execute permissions:

```bash
chmod +x build.sh
./build.sh
```

## What the Scripts Do

1. Clean and compile the backend using Maven (with or without wrapper)

2. Build the frontend with Angular

3. Stop any existing Docker containers

4. Rebuild Docker images from scratch

5. Start up containers at http://localhost:6969

## Project Structure

swapi-backend/     # Spring Boot backend
swapi-frontend/    # Angular frontend
build.bat          # Windows build script
build.sh           # Linux/macOS build script
docker-compose.yml # Docker container configuration
README.md          # This file

## Running Tests

Tests are currently skipped in the scripts <code>(-DskipTests)</code>. You can run them manually:

```bash
# Backend
cd swapi-backend
./mvnw test
```

## Additional Notes

- If Maven isn't installed, the wrapper (mvnw) will be used automatically

- Frontend dependencies are installed only if node_modules is missing

- Default port is 6969, but it can be changed in docker-compose.yml