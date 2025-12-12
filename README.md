# 📦 Fruit Orders API (MongoDB Version)

## 📋 Description

This project represents **Level 3** of the backend learning path. It is a RESTful API designed to manage Fruit Orders for clients using **MongoDB** as the persistence engine.

Unlike previous SQL-based exercises, this project focuses on **NoSQL** concepts, specifically the use of **Embedded Documents**. Instead of creating separate tables for orders and items with foreign keys, the Order Items are stored directly inside the Order document, optimizing read performance and atomicity.

## 🚀 Features

* **Order Management:** Create, Read, Update, and Delete (CRUD) orders.
* **Embedded Documents:** `OrderItem` objects are nested within the `Order` document.
* **NoSQL Persistence:** Uses **Spring Data MongoDB**.
* **Data Validation:** Strict input validation using Jakarta Validation (`@Future` dates, `@Positive` quantities, etc.).
* **Error Handling:** Centralized exception handling returning clean JSON error messages.
* **Dockerized Environment:** Includes `docker-compose` to run the MongoDB database and the application.

## 🛠️ Tech Stack

* **Language:** Java 21 (LTS)
* **Framework:** Spring Boot 3.x
* **Database:** MongoDB
* **Build Tool:** Maven
* **Testing:** JUnit 5, Mockito, MockMvc (TDD approach)
* **Containerization:** Docker & Docker Compose

## 🗄️ Data Model (NoSQL)

The data is stored in a single collection named `orders`. There are no relationships/joins.
The structure follows this JSON schema:

```json
{
  "_id": "657a...",  // MongoDB ObjectId (String)
  "clientName": "Joan Garcia",
  "deliveryDate": "2025-12-25",
  "items": [         // Embedded List of Items
    {
      "fruitName": "Apple",
      "quantityInKilos": 5
    },
    {
      "fruitName": "Banana",
      "quantityInKilos": 2
    }
  ]
}
```
---
Aquí tienes la segunda parte del README.md, formateada correctamente desde la sección de API Endpoints hasta el final.

Markdown

## 🔌 API Endpoints

### 📦 Orders

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| `POST` | `/orders` | Create a new order | `201 Created` |
| `GET` | `/orders` | List all orders | `200 OK` |
| `GET` | `/orders/{id}` | Get order details by ID | `200 OK` |
| `PUT` | `/orders/{id}` | Update an existing order | `200 OK` |
| `DELETE` | `/orders/{id}` | Delete an order | `204 No Content` |

### 📝 Example Payload (Create Order)

**POST** `/orders`

```json
{
    "clientName": "Maria Lopez",
    "deliveryDate": "2025-12-30",
    "items": [
        {
            "fruitName": "Orange",
            "quantityInKilos": 10
        },
        {
            "fruitName": "Kiwi",
            "quantityInKilos": 3
        }
    ]
}
```
Note: The date format must be yyyy-MM-dd and must be in the future.

## 🐳 Running with Docker
**Option 1: Development Mode (Hybrid)**

Recommended for development. Runs only the database in Docker, while you run the Java app in your IDE.

1. Start MongoDB:
```bash
docker-compose up -d mongo-db
```
2. Run the application in IntelliJ/Eclipse.

**Option 2: Full Deployment**
Builds the application image and runs everything together.
```bash
docker-compose up --build
```
The API will be accessible at ``http://localhost:8080``
## 🧪 Testing
The project follows a TDD (Test Driven Development) approach. To run the unit and integration tests:
``./mvnw test``
## 📂 Project Structure
```PlainText
src/main/java/cat/itacademy/s04/t02/n03
├── controllers    # REST Controllers
├── services       # Business Logic & DTO Mapping
├── repository     # MongoRepository Interfaces
├── model          # Domain Entities (Order, OrderItem)
├── dto            # Data Transfer Objects (Records)
└── exception      # Global Exception Handler
```
![cat_order](https://images.unsplash.com/photo-1615678857339-4e7e51ce22db?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)