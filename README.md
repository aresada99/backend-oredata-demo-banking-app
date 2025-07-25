# 🏦 `backend-oredata-demo-banking-app`

## This is the back-end of the demo project developed for the Oredata recruitment process.

This Spring Boot-based backend application is designed to simulate core functionalities of a modern banking system, with a focus on clean architecture, secure authentication, and data integrity.

### 🔐 Authentication & Authorization

- **Spring Security** is used to secure all endpoints with role-based access control.
- **JWT (JSON Web Tokens)** is implemented to authenticate users and protect routes after login.
- Unauthorized and unauthenticated access is handled gracefully with standardized error responses.

### 🔄 API Design & Documentation

- All RESTful endpoints are clearly documented using **Swagger UI**, providing an interactive API interface for testing and exploration.

### 🔁 Database Transactions

- For critical operations such as **money transfers**, **@Transactional** is used to ensure atomicity and consistency — the operation either completes fully or not at all, ensuring no partial updates or data corruption occur.

### 🧩 Modular Structure

- The project follows best practices in structuring layers (Controller, Service, Repository) to maintain separation of concerns and promote scalability and maintainability.

### 📦 Technologies Used

- Java 17  
- Spring Boot 3.5.3  
- Spring Security  
- Spring Data JPA  
- Spring Validation  
- H2 (In-Memory Database for easily and quickly testing the app)  
- JWT (via `jjwt`)  
- Lombok  
- Swagger UI (via Springdoc OpenAPI)  
- Maven
  
> **Note:**  
> H2 Database is used solely for ease of testing and to allow quick evaluation of the assignment.  
> To switch to a real database, it is sufficient to configure the connection to your preferred DBMS (e.g., MySQL or PostgreSQL).  
> All entities, repositories, and data access layers are designed to be compatible with production-grade databases and are ready for live deployment.

---

## 🔧 `application.properties` File

Make sure to define the base URL for your API in the `.env` file as below:

```env
spring.application.name=banking-demo

jwt.secret=your_jwt_secret
jwt.expirationMs=86400000

spring.h2.console.enabled=true

spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:mem:testdb

spring.datasource.username=sa
spring.datasource.password=

cors.allowed-origin=https://your_target_url
```

---

## 🚀 Project Overview

All API endpoints shown in the image. "/login" and "/register" routes are public routes. Also Swagger and h2-console related routes are public in order to easily test the application. These routes are just for testing, not suitable for production. 

Other API endpoints are protected. Users must be authenticated before using these endpoints. 

<img width="1080" height="681" alt="back-4" src="https://github.com/user-attachments/assets/cc45cad0-9ca4-45ed-b582-d65370c1f0c4" />

These images shows the DB side of the application. All passwords are directly encrypted and stored in the DB hashed. So every password are storing safely in the DB. 

<img width="1080" height="680" alt="back-1" src="https://github.com/user-attachments/assets/9b9ea96c-bf42-4192-8baf-a8d72eef496f" />
<img width="1080" height="682" alt="back-2" src="https://github.com/user-attachments/assets/f219e392-59bd-4440-a87c-f9fdb0230832" />
<img width="1080" height="682" alt="back-3" src="https://github.com/user-attachments/assets/c30dbfc6-68fc-40fb-9cb4-cbec50244e77" />

---

