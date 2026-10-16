# AxoClash Java Template Bot

Bot API for the AxoClash game implemented with Spring Boot.

## Getting Started

### Prerequisites
- Java 25
- Maven 3.x

### Running the Application

#### With Maven
```bash
mvn spring-boot:run
```

The application will start on **port 9000**.

#### With Docker
```bash
docker build -t axoclash-bot-java .
docker run -p 9000:9000 axoclash-bot-java
```

Or with Docker Compose:
```bash
docker-compose up
```

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `PORT`   | Listen port of the server | `9000` |

```bash
PORT=8080 mvn spring-boot:run
```

When running with Docker, override it with the `PORT` environment variable (and map the port accordingly):
```bash
docker run -e PORT=8080 -p 8080:8080 axoclash-bot-java
```

The final image runs on Alpine with a custom minimal JRE built by `jlink` (only the JDK modules the app actually uses), the Spring Boot jar extracted in layers for faster startup, and Spring AOT enabled (`process-aot` runs at build time) for near-instant startup.

## API Endpoints

- `GET /api/v1/bots/info` - Get bot information
- `POST /api/v1/bots/play` - Play an action (returns random action)

## Development

### Build
```bash
mvn clean compile
```

### Run Tests
```bash
mvn test
```

### Code Style Check
```bash
mvn checkstyle:check
```

## Configuration

The application uses Maven profiles:
- `dev` (default) - Development environment
- `test` - Testing environment  
- `rec` - Recette/staging environment
- `prod` - Production environment