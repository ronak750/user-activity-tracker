# User Activity API - Java Spring Boot

## 📌 Overview
This is a Spring Boot-based REST API that provides user activity details. It supports filtering by user ID and action type, with pagination and sorting features. The API retrieves user actions like `LOGIN`, `ORDER_CREATION`, `CANCEL`, and more, stored in an H2 in-memory database.

## 🚀 Features
- Retrieve user actions with optional filters (`userId`, `userAction`).
- Supports pagination (`limit`, `pageNo`) and sorting (`asc` or `desc`).
- Uses **Java Records** for DTOs to ensure immutability and cleaner code.
- Implements **Global Exception Handling** to manage errors gracefully.
- Built-in **H2 database** for easy setup.

---

## 🔧 Setup & Run Instructions
### 1️⃣ Clone the repository:
```sh
git clone https://github.com/ronak750/user-activity-tracker.git
cd user-activity-tracker
```

### 2️⃣ Build & Run the project:
```sh
mvn spring-boot:run
```

### 3️⃣ Open H2 Console (Optional for DB Debugging):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

---

## 📡 API Endpoints
### ✅ Get User Activities
#### **Request:**
```http
GET /api/v1/user/actions?userId=1&userAction=LOGIN&limit=10&sortingOrder=desc&pageNo=1
```
#### **Response:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "number": "1234567890",
  "details": [
    {
      "userDevice": "Android",
      "logInTime": "2025-01-01T10:00:00",
      "userAction": "LOGIN"
    }
  ]
}
```

---

## 🚨 Error Handling
| Scenario | Status Code | Response |
|----------|------------|----------|
| Invalid `userAction` provided | `400 Bad Request` | `{ "error": "Invalid userAction provided. Allowed values: [LOGIN, ORDER_CREATION, CANCEL, ...]" }` |
| User not found | `404 Not Found` | `{ "error": "User not found" }` |
| Invalid pagination params | `400 Bad Request` | `{ "error": "Limit and page number must be greater than 0" }` |
