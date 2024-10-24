# Project EdgeCraft BackEnd

## Overview

Project EdgeCraft BackEnd is a Spring Boot application designed to manage user authentication, project management, and file uploads. It leverages various Spring Boot starters, JWT for security, and ModelMapper for object mapping.

## Features

- User Registration and Authentication
- Project Management (CRUD operations)
- File Uploads for User Profiles and Project Thumbnails
- JWT-based Security
- RESTful API Endpoints

## Technologies Used

- Java 21
- Spring Boot
- Maven
- MySQL
- JWT
- ModelMapper
- Lombok

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/projectEdgeCarftBackEnd.git
    cd projectEdgeCarftBackEnd
    ```

2. **Configure the database:**
    - Update the `src/main/resources/application.properties` file with your MySQL database credentials.

3. **Build the project:**
    ```bash
    mvn clean install
    ```

4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## Configuration

### `application.properties`

```ini
spring.application.name=projectEdgeCarftBackEnd
spring.datasource.url=jdbc:mysql://localhost:3306/Project_EdgeCraft?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Ijse@2024
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.open-in-view=false
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
token.key=your_jwt_secret_key

```

# EdgeCraft API Documentation

## Authentication Endpoints

### Sign Up
- **Endpoint:** `POST /edge_craft/v1/auth/signup`
- **Description:** Register a new user.

### Sign In
- **Endpoint:** `POST /edge_craft/v1/auth/signin`
- **Description:** Authenticate a user and retrieve access tokens.

### Refresh Token
- **Endpoint:** `POST /edge_craft/v1/auth/refresh`
- **Description:** Refresh the access token using a refresh token.

## User Management Endpoints

### Register User
- **Endpoint:** `POST /edge_craft/v1/users`
- **Description:** Register a new user into the system.

### Login User
- **Endpoint:** `GET /edge_craft/v1/users/login`
- **Description:** Login the user and return user data.

### Get User
- **Endpoint:** `GET /edge_craft/v1/users/{email}`
- **Description:** Retrieve details of a user by email.

### Update User
- **Endpoint:** `PUT /edge_craft/v1/users`
- **Description:** Update user details.

### Get User Profile Picture
- **Endpoint:** `GET /edge_craft/v1/users/profilePic/{username}`
- **Description:** Retrieve the profile picture of a user by their username.

## Project Management Endpoints

### Save Project
- **Endpoint:** `POST /edge_craft/v1/projects`
- **Description:** Create and save a new project.

### Update Project
- **Endpoint:** `PUT /edge_craft/v1/projects`
- **Description:** Update an existing project.

### Get Project
- **Endpoint:** `GET /edge_craft/v1/projects/{projectId}`
- **Description:** Retrieve details of a specific project by its ID.

### Get All Projects by User
- **Endpoint:** `GET /edge_craft/v1/projects/allProjects/{email}`
- **Description:** Retrieve all projects created by a user with the provided email.

### Get All Projects
- **Endpoint:** `GET /edge_craft/v1/projects/allProjects`
- **Description:** Retrieve all projects from the system.

### Delete Project
- **Endpoint:** `DELETE /edge_craft/v1/projects/{projectId}`
- **Description:** Delete a project by its ID.

### Get Project Thumbnail
- **Endpoint:** `GET /edge_craft/v1/projects/getProjectThumbnail/{projectTitle}`
- **Description:** Retrieve the thumbnail for a project by its title.

### Get Last Project
- **Endpoint:** `GET /edge_craft/v1/projects/getLastProject`
- **Description:** Retrieve the most recent project added to the system.

### Get Last Project Image
- **Endpoint:** `GET /edge_craft/v1/projects/getLastProjectImg`
- **Description:** Retrieve the image of the most recent project.

### Get UI Projects
- **Endpoint:** `GET /edge_craft/v1/projects/getUiProject`
- **Description:** Retrieve all UI-related projects.

### Get FrontEnd Projects
- **Endpoint:** `GET /edge_craft/v1/projects/getFrontEndProject`
- **Description:** Retrieve all frontend-related projects.

### Get BackEnd Projects
- **Endpoint:** `GET /edge_craft/v1/projects/getBackEndProject`
- **Description:** Retrieve all backend-related projects.

## License

This project is licensed under the MIT License.  
For more details, view the [LICENSE](LICENSE).

## Acknowledgements

- **Spring Boot**
- **JWT**
- **ModelMapper**
- **Lombok**
- **MySQL**
