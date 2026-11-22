# 🚗 Car Management & Fuel Tracking System

A robust, multi-module Java enterprise application designed for vehicle record management and precise fuel consumption analytics. The system features a **Spring Boot REST backend**, a custom low-level **Java Servlet**, and an independent **interactive CLI client** built using Java's native `HttpClient`.

---

## 📌 Architecture & Design Principles

The application is structured into two decoupled modules adhering strictly to Object-Oriented Programming (OOP) and Enterprise Java standards:

car-fuel-management/├── src/main/java/com/unilak/carfuel/      # Spring Boot Backend & Servlet│   ├── config/                           # Servlet and App Configuration│   ├── controllers/                      # Spring MVC REST Controllers│   ├── dto/                              # Data Transfer Objects│   ├── exceptions/                       # Global Exception Handlers (@ControllerAdvice)│   ├── models/                           # JPA Entities (Inheriting from BaseEntity)│   ├── repositories/                     # Spring Data JPA Repositories│   └── services/                         # Business Logic & Calculation Layer└── cli-client/                           # Independent CLI Module└── src/main/java/com/unilak/carfuel/cli/└── CarCliApp.java                # Interactive CLI using java.net.http.HttpClient
### Key Engineering Highlights
- **Layered Enterprise Architecture:** Clean separation of concerns across Models, DTOs, Repositories, Services, Controllers, and Exception Handlers.
- **Strict OOP & Polymorphism:** Core domain entities (`Car`, `FuelEntry`) extend an abstract `BaseEntity` enforcing encapsulation, parameterized constructors, and uniform string representation.
- **Relational Data Mapping:** JPA / Hibernate bidirectional `@OneToMany` and `@ManyToOne` relationships with cascade updates and foreign key constraints.
- **Dual Exposure Pattern:** Metrics are accessible via both modern REST endpoints and an explicitly registered low-level `HttpServlet` (`/servlet/fuel-stats`).
- **Zero-Dependency CLI:** The command-line client interacts with the server solely via standard Java 11+ `java.net.http.HttpClient` without external HTTP wrappers.

---

## 🛠️ Tech Stack & Requirements

- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 3.3.4 (Spring Web, Spring Data JPA)
- **Database:** MySQL 8.0+
- **Persistence:** Hibernate ORM
- **Build Tool:** Apache Maven
- **Protocols:** REST (JSON), HTTP/1.1 Servlets

---

## 🚀 Getting Started

### 1. Prerequisites
Ensure you have the following installed locally:
- JDK 21 or later
- MySQL Server
- Git & Maven

### 2. Database Configuration
Create a database in your local MySQL instance:
```sql
CREATE DATABASE car_fuel_db;
Update your credentials in src/main/resources/application.properties:Propertiesspring.datasource.url=jdbc:mysql://localhost:3306/car_fuel_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Build & Run the Backend ServerFrom the project root:Bash# Build project
mvn clean install

# Run the Spring Boot application
mvn spring-boot:run
The server will initialize on http://localhost:8080 and automatically create the required database tables (cars, fuel_entries).

💻 CLI Client UsageOpen a separate terminal window and run the standalone CLI application:Bash# Compile and run the CLI client
cd cli-client
mvn compile exec:java -Dexec.mainClass="com.unilak.carfuel.cli.CarCliApp"
Supported Interactive CommandsRegister a Vehicle:Plaintextcreate-car --brand Toyota --model Corolla --year 2018
Add Fuel Entry:Plaintextadd-fuel --carId 1 --liters 40 --price 52.5 --odometer 45000
Log a Subsequent Entry & Check Stats:Plaintextadd-fuel --carId 1 --liters 20 --price 26.25 --odometer 45300
fuel-stats --carId 1
Sample Output:PlaintextTotal fuel: 60.0 L
Total cost: 78.75
Average consumption: 6.67 L/100km
🌐 API & Servlet EndpointsMethodEndpointDescriptionHandlerPOST/api/carsRegister a new carREST ControllerPOST/api/fuel-entriesLog a fuel entry for a carREST ControllerGET/api/cars/{id}/statsFetch aggregate fuel statsREST ControllerGET/servlet/fuel-stats?carId=1Direct Servlet statistics handlerCustom HttpServlet👥 ContributorsEminou AbdouBayambaze Jean FredMaaz Montasir Osman
📄 LicenseThis project is developed as an academic software engineering assignment for UNILAK and is released under the MIT License.
