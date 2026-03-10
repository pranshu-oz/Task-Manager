# Task Manager

A simple Spring Boot REST API to manage tasks with JWT-based authentication and role-based authorization. Created by Pranshu Shukla.

## Repository structure (key packages)

- `pranshu.task_manager` - application root
  - `config` - OpenAPI/Swagger configuration (`SwaggerConfiguration.java`)
  - `controller` - REST controllers (`TaskController`, `LoginController`)
  - `dto` - request/response DTOs (`TaskRequest`, `TaskResponse`, `TaskUserRequest`, `TaskUserResponse`)
  - `exception` - global exception handler (`GlobalExceptionHandler.java`)
  - `filter` - security filter for JWT (`JwtFilter.java`)
  - `model` - JPA entities (`Task`, `TaskUser`)
  - `repository` - Spring Data JPA repositories (`TaskRepository`, `TaskUserRepository`)
  - `security` - security configuration (`TaskManagerSecurity.java`)
  - `service` - business logic and JWT helper (`TaskService`, `TaskUserService`, `JwtService`)

## Tech stack

- Java 17
- Spring Boot (webmvc, security, data-jpa)
- H2/MySQL (project currently configured for MySQL)
- Maven wrapper (use `mvnw.cmd` on Windows)
- JJWT for token handling
- Springdoc OpenAPI (Swagger UI)

## Requirements

- JDK 17
- Maven (or use the included Maven wrapper)
- MySQL (or change datasource to an embedded DB for quick testing)

## Configuration

Configuration lives in `src/main/resources/application.properties`. Example values in this project:

```
spring.application.name=task_manager
spring.datasource.username=root
spring.datasource.password=user
spring.datasource.url=jdbc:mysql://localhost:3306/task
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
server.port=9999
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework.security=DEBUG
```

Create the MySQL database `task` (or change `spring.datasource.url`) before starting.

## Build & Run (Windows PowerShell)

Using the project's Maven wrapper (recommended):

```powershell
# build
.\mvnw.cmd -DskipTests package

# run
.\mvnw.cmd spring-boot:run
```

Or run the packaged jar:

```powershell
java -jar target\task_manager-0.0.1-SNAPSHOT.jar
```

The application listens on port `9999` by default (see `application.properties`).

## API Endpoints

The project exposes the following main endpoints:

- POST /user
  - Create a new user. Request body: `TaskUserResponse` JSON (username, password, roles).
- POST /login
  - Authenticate and receive a JWT token. Request body: `TaskUserRequest` (username, password). Returns a JWT string when credentials are valid.

Task management (protected by JWT and role rules):
- POST /task
  - Create a Task. Requires role `MANAGER` (see `TaskManagerSecurity`). Request body: `TaskRequest` JSON (title, discription, status).
- GET /task
  - List all tasks. Requires authentication.
- GET /task/{id}
  - Get a single task by id. Requires authentication.
- PUT /task/{id}
  - Update a task by id. Requires authentication (role checks in `TaskManagerSecurity` may apply).
- DELETE /task/{id}
  - Delete a task by id. Requires role `ADMIN`.
- DELETE /task
  - Delete all tasks. Requires role `ADMIN`.

All protected endpoints expect an `Authorization` header with the bearer token:

```
Authorization: Bearer <JWT_TOKEN>
```

Example: authenticate, then use token to list tasks (PowerShell/curl):

```powershell
# login -> returns token string
$token = (Invoke-RestMethod -Method Post -Uri http://localhost:9999/login -Body (@{username='admin';password='pass'} | ConvertTo-Json) -ContentType 'application/json')

# use token to call GET /task
Invoke-RestMethod -Method Get -Uri http://localhost:9999/task -Headers @{ Authorization = "Bearer $token" }
```

## DTO shapes (examples)

TaskRequest
```json
{
  "title": "Buy groceries",
  "discription": "Buy milk and eggs",
  "status": "OPEN"
}
```

TaskUserResponse (create user)
```json
{
  "username": "manager1",
  "password": "secret",
  "roles": "MANAGER"
}
```

## Swagger / OpenAPI

Swagger UI is configured via `springdoc-openapi` and available (in this project) at:

- http://localhost:9999/swagger-ui.html
- or http://localhost:9999/swagger-ui/index.html

The security scheme `BearerAuth` is defined so you can paste a JWT in the UI to test protected endpoints.

## Important notes & suggestions

- JWT secret is hard-coded in `pranshu.task_manager.service.JwtService` (`SECRET` field). For production, move the secret to an environment variable or an external secrets manager.
- The token expiry call in `JwtService.generateTocken()` multiplies `System.currentTimeMillis()` by `1000*60` which likely produces an incorrect expiration date (should add milliseconds to current time). Consider changing it to `new Date(System.currentTimeMillis() + 1000 * 60 * <minutes>)`.
- Passwords are stored using BCrypt in `TaskUserService.addNewUser()` (good). Ensure TLS/HTTPS in production for token transport.
- Role-based access in `TaskManagerSecurity` currently assigns `POST` to `MANAGER` and `DELETE` to `ADMIN`. Review and adjust rules to match your intended RBAC model.

## Tests

There is a basic test class at `src/test/java/pranshu/task_manager/TaskManagerApplicationTests.java`. Run tests with:

```powershell
.\mvnw.cmd test
```

## Where to look in code

- Application entry: `src/main/java/pranshu/task_manager/TaskManagerApplication.java`
- REST controllers: `src/main/java/pranshu/task_manager/controller/`
- Services: `src/main/java/pranshu/task_manager/service/`
- Security: `src/main/java/pranshu/task_manager/security/TaskManagerSecurity.java`

## License

This repository does not include a license file. Add one if you plan to publish or share the code.

---

If you'd like, I can also:
- add a CONTRIBUTING.md or LICENSE
- fix the JWT expiration bug and externalize the secret
- add a small integration test showing login + authenticated request

Feel free to tell me which of those you'd like next.
