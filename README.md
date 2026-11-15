# spring-boot-mongock

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-mongock/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-mongock/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-47A248?logo=mongodb)

Sample Spring Boot application that runs **MongoDB data migrations** with [Mongock](https://mongock.io).
On startup Mongock scans the changelog package, acquires a distributed lock, and executes
pending `@ChangeSet`s — here it seeds the `users` collection with initial data.

## Tech Stack

| Component      | Version                                   |
|----------------|-------------------------------------------|
| Java           | 25 (Temurin)                              |
| Spring Boot    | 4.1.x                                     |
| Mongock        | 4.3.8 (`mongock-spring-v5` + `mongodb-springdata-v3-driver`) |
| MongoDB        | 8.0 (Docker Compose / Testcontainers)     |
| Build tool     | Maven (wrapper included)                  |
| Others         | Lombok, Spring Web, Spring Boot DevTools  |

## Project Structure

```
src/main/java/id/my/hendisantika/mongock
├── SpringBootMongockApplication.java   # @EnableMongock entry point
├── migration/DatabaseChangeLog.java    # @ChangeLog / @ChangeSet migrations
├── model/User.java                     # users document
└── repository/UserRepository.java      # Spring Data repository
src/test/java/id/my/hendisantika/mongock
├── SpringBootMongockApplicationTests.java  # verifies migration seeds users
└── TestcontainersConfiguration.java        # MongoDB container via @ServiceConnection
```

## Configuration

`src/main/resources/application.properties`

```properties
spring.application.name=spring-boot-mongock
spring.mongodb.uri=mongodb://localhost:27017/users
spring.mongodb.database=users
mongock.change-logs-scan-package=id.my.hendisantika.mongock.migration
```

## Prerequisites

- JDK 25
- Docker (for MongoDB via Docker Compose and for Testcontainers in tests)

## Running the Application

`spring-boot-docker-compose` starts MongoDB from `compose.yaml` automatically:

```bash
./mvnw spring-boot:run
```

Or start MongoDB manually first:

```bash
docker compose up -d
./mvnw spring-boot:run
```

Check the seeded data:

```bash
docker exec -it mongock mongosh users --eval 'db.users.find()'
```

## Writing a Migration

Add a method annotated with `@ChangeSet` inside a `@ChangeLog` class in the
`id.my.hendisantika.mongock.migration` package:

```java
@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "seedDataBaseWithUsers", author = "hendisantika")
    public void seedDataBase(MongockTemplate mongockTemplate) {
        // insert / update / index documents here
    }
}
```

Every change set is executed once; Mongock records it in the `mongockChangeLog` collection.

## Testing

Tests spin up a real MongoDB 8.0 container with Testcontainers, so Docker must be running:

```bash
./mvnw test
```

## CI/CD & Dependency Updates

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on JDK 25 for every push and
  pull request to `main`, then submits the Maven dependency graph on push.
- **Dependabot** (`.github/dependabot.yml`): daily grouped updates for Maven and GitHub Actions,
  weekly updates for Docker Compose images, with labels and conventional commit prefixes.

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
