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

## Pièges et zone rouge

Les modèles acceptent `PLACE_TRAP` avec `coordinates` et exposent `board[y][x].trap`
(`placedBy`, `placedAtTurn`, `armedFromTurn`, `expiresAfterTurn`) et
`bot.data.nextTrapTurn` (10 initialement). La stratégie fournie reste un exemple :
elle n’exploite pas automatiquement les pièges.

Une pose remplace le piège présent sur la case. Posé au tour T, il est actif de
T+1 à T+10 inclus ; la recharge autorise une nouvelle pose à T+10. Après chaque
action, les pièges sous les deux bots infligent 1 PV puis disparaissent, y compris
sous leur créateur. Jouer deuxième peut donc empêcher de s’en échapper à temps.
La couleur du créateur est purement visuelle. Les pièges ne bloquent pas les tirs.
`currentTurn` compte les tours terminés : la prochaine action résout `currentTurn+1`.

La zone rouge est annoncée aux tours 70/80/90/100 et active aux tours 75/85/95/105,
du bord vers le centre : 1 PV par bot en zone active en fin de tour global.
