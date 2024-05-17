# Project Management API

This project is a RESTful API for managing projects, built with Spring Boot. It allows you to create, read, update, and delete project records.

## Overview

This API provides a way to manage project data. Each project has the following attributes:
- `id`: Unique identifier for the project
- `name`: Name of the project
- `description`: Description of the project
- `startDate`: Start date of the project
- `endDate`: End date of the project

## Getting Started

### Installing

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/project-management-api.git
   cd project-management-api

The application will start on `http://localhost:8080`.
### Database Configuration

#### Configure the H2 In-Memory Database

To configure the H2 in-memory database, add the following settings to your `application.properties` file:

```properties
# application.properties

# H2 database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
```

#### Define the Schema for the 'Project' Entity

Create the `Project` entity class with the required attributes: `id`, `name`, `description`, `startDate`, and `endDate`.
## API Endpoints

### Create Project

- **URL**: `/api/projects`
- **Method**: `POST`
- **Description**: Creates a new project.
- **Request Body**:
  ```json
  {
    "name": "New Project",
    "description": "Description of the new project",
    "startDate": "2023-06-01",
    "endDate": "2023-06-30"
  }
  ```
- **Response**:
  ```json
  {
    "id": 2,
    "name": "New Project",
    "description": "Description of the new project",
    "startDate": "2023-06-01",
    "endDate": "2023-06-30"
  }
  ```

### Get All Projects

- **URL**: `/api/projects`
- **Method**: `GET`
- **Description**: Retrieves a list of all projects.
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Project 1",
      "description": "Description of Project 1",
      "startDate": "2023-05-01",
      "endDate": "2023-05-31"
    },
    {
      "id": 2,
      "name": "Project 2",
      "description": "Description of Project 2",
      "startDate": "2023-06-01",
      "endDate": "2023-06-30"
    }
  ]
  ```

### Get Project by ID

- **URL**: `/api/projects/{id}`
- **Method**: `GET`
- **Description**: Retrieves a project by its ID.
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Project 1",
    "description": "Description of Project 1",
    "startDate": "2023-05-01",
    "endDate": "2023-05-31"
  }
  ```
- **Error Response**:
  - **Status**: `404 Not Found`
  - **Body**:
    ```json
    {
      "timestamp": "2023-05-17T14:34:56.123",
      "message": "Project not found with id: 1",
      "details": "uri=/api/projects/1"
    }
    ```

### Update Project

- **URL**: `/api/projects/{id}`
- **Method**: `PUT`
- **Description**: Updates an existing project.
- **Request Body**:
  ```json
  {
    "name": "Updated Project",
    "description": "Updated description of the project",
    "startDate": "2023-06-01",
    "endDate": "2023-06-30"
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "name": "Updated Project",
    "description": "Updated description of the project",
    "startDate": "2023-06-01",
    "endDate": "2023-06-30"
  }
  ```

### Delete Project

- **URL**: `/api/projects/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a project by its ID.
- **Response**:
  ```json
  {
    "message": "Deleted successfully"
  }
  ```
- **Error Response**:
  - **Status**: `404 Not Found`
  - **Body**:
    ```json
    {
      "timestamp": "2023-05-17T14:34:56.123",
      "message": "Project not found with id: 1",
      "details": "uri=/api/projects/1"
    }
    ```
## Testing

### Unit Tests

Unit tests for the service and controller layers are written using JUnit and Mockito. These tests ensure that the service and controller methods behave as expected.

To run the tests, use the following command:
```bash
mvn test
```


