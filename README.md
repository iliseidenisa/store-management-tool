# Store Management Tool API

## Features
- User registration and login (Basic Authentication)
- Product management operations: find products, find product by name, update price, delete product
- Role-based access control (ADMIN, MANAGER, CUSTOMER)
- Exception handling
- Unit tests

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/iliseidenisa/store-management-tool.git
cd store-management-tool
```
### 2. Set up the database
```bash
docker-compose up
```
-> Update database configuration in application.properties
-> Initialization of user and user roles by running DataLoader

#### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* postgres: [`postgres:latest`](https://hub.docker.com/_/postgres)


### 3. Build project
```bash
mvn clean install
```

### 4. Run the application
```bash
mvn spring-boot:run
```

## API Documentation

Swagger UI documentation available at:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)