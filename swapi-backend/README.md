# Star Wars Backend

A lightweight Spring Boot API that connects to the public [SWAPI](https://swapi.dev/) and provides filtered and sorted lists of people and planets.

## Features

- Search and sort **Star Wars** characters (`/api/people`) and planets (`/api/planets`)
- Parameters for:
    - `search`: filter by name (partial match)
    - `sortBy`: field to sort by (`name`, `created`)
    - `direction`: ascending (`asc`) or descending (`desc`)
- Dockerized for easy deployment

## Technologies

- Java 17
- Spring Boot
- Lombok
- Jackson
- Docker

## Build and Run

### Prerequisites

- Java 17
- Maven

### Local Development

```bash
# Build the JAR
mvn clean package

# Run the app
java -jar target/starwars-backend-0.0.1-SNAPSHOT.jar
```

The app runs on port 8080 by default.

### Docker
```bash
# Build the image
docker build -t starwars-backend .

# Run the container
docker run -p 8080:8080 starwars-backend
```

## API Endpoints

<code>/api/people</code>

Returns a list of characters from Star Wars.

#### Parameters:

<code>search</code> (optional): text to search by name

<code>sortBy</code> (optional): name (default) or created

<code>direction</code> (optional): asc (default) or desc

#### Example:

```http
GET /api/people?search=luke&sortBy=created&direction=desc
```

<code>/api/planets</code>

Returns a list of planets.

<b>Parameters</b>: same as /people.

## Example Response
```json
[
  {
    "name": "Tatooine",
    "created": "2014-12-09T13:50:49.641000Z"
  }
]
```

## Testing
```bash
mvn test
```