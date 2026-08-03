# Student Management System

A complete Java backend project demonstrating the evolution of Java web development — from raw JDBC to modern Spring Boot REST APIs.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Build Tool | Maven |
| Database | H2 (In-Memory) |
| ORM | JPA + Hibernate |
| API Style | RESTful JSON |
| Legacy Stack | JDBC + Servlets + JSP |

src/main/java/com/example/demo/
├── DemoApplication.java          # Spring Boot entry point
├── model/
│   └── Student.java              # JPA @Entity (ORM)
├── repository/
│   └── StudentRepository.java    # Spring Data JPA
├── controller/
│   └── StudentController.java    # REST API endpoints
├── dto/
│   └── ApiResponse.java          # Standard response wrapper
└── exception/
└── GlobalExceptionHandler.java # @RestControllerAdvice
legacy/                             # Original implementation
├── Student.java                    # Plain POJO (Model)
├── JdbcStudentDao.java             # Raw JDBC data access
├── StudentServlet.java             # Servlet Controller
└── students.jsp                    # JSP View (HTML/CSS)
src/main/resources/
└── application.properties          # H2 + JPA config

## Run Locally

```bash
./mvnw spring-boot:run
Base URL: http://localhost:8080
H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:studentdb
Username: sa