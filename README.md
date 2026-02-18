Smart Investment Portfolio Tracker
A full-stack, secure investment management system built with Java 21, Spring Boot 3.2.5, and React (Vite). This project implements stateless authentication, role-based access control, and a modern dashboard for tracking asset growth.

Technical Highlights
Stateless JWT Authentication: Implemented a secure authentication flow using JSON Web Tokens (JWT), ensuring the application is stateless and horizontally scalable.

Role-Based Access Control (RBAC): Distinct permissions for USER and ADMIN roles, protecting sensitive system statistics and administrative endpoints.

Security First: Passwords are securely hashed using BCrypt, and all requests are protected via a custom Spring Security filter chain.

Modern UI/UX: A responsive React dashboard featuring real-time portfolio value calculations, summary cards, and a SaaS-inspired interface.

Automated Documentation: Integrated OpenAPI/Swagger for interactive API testing and documentation.

 Tech Stack
Backend: Java 21, Spring Boot 3.2.5, Spring Security, Spring Data JPA.

Database: PostgreSQL.

Frontend: React 18, Vite, Axios, React Router.

Documentation: SpringDoc OpenAPI (Swagger).

 Scalability Note (For Reviewers)
To ensure this application can scale to handle millions of users and high-frequency data:

Horizontal Scaling: Since the backend is stateless (JWT), multiple instances can be deployed behind a Load Balancer.

Caching: I would implement Redis to cache real-time asset prices and user portfolio summaries to reduce database load.

Database Optimization: Implementing indexing on the user_id column in the products table would ensure constant-time lookups as the dataset grows.

Containerization: The project is designed to be easily containerized using Docker for consistent deployment across environments.

 Getting Started
Prerequisites
JDK 21 or higher

Node.js & npm

PostgreSQL

1. Backend Setup
Configure your PostgreSQL credentials in src/main/resources/application.properties.

Run the application:

Bash

mvn clean install
mvn spring-boot:run
API Documentation will be available at: http://localhost:8081/swagger-ui/index.html.

2. Frontend Setup
Navigate to the frontend directory:

Bash

cd Frontend1
Install dependencies and start the dev server:

Bash

npm install
npm run dev
Access the UI at: http://localhost:5174

API Documentation
The API is fully documented using Swagger. It includes authentication endpoints and CRUD operations for investment management
