# 🚀 Running the DDD Lease API

This guide walks you through running the Lease API locally.

---

## 📋 Prerequisites

Make sure you have the following installed:

- ☕ Java 21 or higher
- 🐘 Maven 3.6+
- 🐳 Docker
- 🧠 IDE (e.g., IntelliJ IDEA, VS Code – optional but helpful)

---

## 💻 Running the Application Locally

### 🔁 1. Clone the Repository

```bash
   git clone https://github.com/hakktastic/ddd-lease-api.git
   cd ddd-lease-api
   git checkout implement-api
```

### 🧪 2. Build the Project

```bash
   mvn clean install
```

### 🚀 3. Start the Application

```bash
   mvn spring-boot:run
```

---

## 🌐 The API will be available at:

```bash
   curl http://localhost:9090//api/v1/quotes
```

For running requests, see [http-requests-quotes.http](./http-requests/http-requests-quotes.http).