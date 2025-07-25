# 🏦 `backend-oredata-demo-banking-app`

## Overview

This is the back-end of a demo banking application developed as part of the Oredata recruitment process.  
Built with **Spring Boot**, it simulates the essential backend functionalities of a modern banking system with a strong focus on **clean architecture**, **security**, **data consistency**, and **modularity**.

---

## 🔐 Authentication & Authorization

- Secured with **Spring Security** and **JWT-based authentication**.
- Implements **role-based access control** to restrict protected routes.
- User passwords are **securely hashed and encrypted** before being stored in the database, ensuring they are never saved in plain text.
- Public endpoints include `/login`, `/register`, and developer tools like Swagger and H2 Console (for testing purposes only).
- Unauthorized access is handled with clear and standardized error responses.

---

## 🔄 API Documentation

- All endpoints are documented using **Swagger UI**, which provides an interactive API explorer for quick testing and verification.
- Swagger is enabled and publicly accessible to simplify evaluation of the assignment.

---

## 🔁 Transaction Management

- Critical operations like **money transfers** are wrapped with **`@Transactional`** to ensure **atomicity** and **data consistency**.
- In the event of any failure, transactions are rolled back automatically to maintain database integrity.

---

## 🧩 Modular Codebase

- Follows a layered architecture to maintain clear separation of concerns:
  - `Controller` – handles incoming HTTP requests and outgoing responses.
  - `Service` – contains business logic and orchestrates operations between layers.
  - `Repository` – manages data persistence and retrieval using Spring Data JPA.
  - `Model` – defines the core domain entities mapped to database tables.
  - `DTO (Data Transfer Objects)` – used to transfer data between client and server, ensuring encapsulation and validation.
  - `Config` – holds configuration classes such as security setup, JWT filters, CORS policies, and Swagger configuration.
- Designed for scalability, maintainability, and easy unit/integration testing.

---

## 📦 Technologies Used

- Java 17  
- Spring Boot 3.5.3  
- Spring Security  
- Spring Data JPA  
- Spring Validation  
- H2 (In-Memory Database for testing)  
- JWT (via `jjwt`)  
- Lombok  
- Swagger UI (via Springdoc OpenAPI)  
- Maven

---

> **Note:**  
> H2 Database is used solely for ease of testing and quick evaluation.  
> To migrate to a real database (such as MySQL or PostgreSQL), it's sufficient to update the database configuration.  
> All entities, repositories, and services are already designed to work seamlessly with production-grade relational databases.

---

## 🔧 Configuration – `application.properties`

Set your backend configurations in `application.properties` as shown below:

```properties
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

