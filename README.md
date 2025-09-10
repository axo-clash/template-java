# AxoClash Java Template Bot

Bot API for the AxoClash game implemented with Spring Boot.

## Getting Started

### Prerequisites
- Java 21
- Maven 3.x

### Running the Application

#### With Maven
```bash
mvn spring-boot:run
```

#### With Docker
```bash
docker-compose up
```

The application will start on **port 6000**.

## API Endpoints

- `GET /info` - Get bot information
- `POST /play` - Play an action (returns random action)

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