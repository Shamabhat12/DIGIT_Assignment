# DIGIT Assignment - Advocate Registration Module

## Overview

This project is developed as part of the **DIGIT Developer Certification (Level 1)** assignment.

The module implements an **Advocate Registration Service** using **Java Spring Boot** by following the DIGIT Backend Developer Guidelines.

The service exposes REST APIs for creating, updating, and searching advocate registration applications while integrating with core DIGIT services.

---

# Features

- Advocate Registration
- Create Advocate API
- Update Advocate API
- Search Advocate API
- Workflow Integration
- ID Generation (IDGen)
- Master Data Management (MDMS)
- Persister Integration
- Request Validation
- JUnit Test Cases
- Postman Collection

---

# Technology Stack

- Java 17
- Spring Boot
- Maven
- PostgreSQL
- Flyway Migration
- Jackson
- Lombok
- JUnit 5
- Mockito
- DIGIT Services

---

# DIGIT Integrations

This project integrates with the following DIGIT platform services:

| Service | Purpose |
|----------|---------|
| IDGen | Generates unique Advocate Application IDs |
| MDMS | Fetches master data |
| Persister | Persists advocate application data |
| Workflow | Handles application workflow |

---

# REST APIs

## 1. Create Advocate

**Endpoint**

```
POST /advocate/v1/_create
```

**Description**

Creates a new advocate registration application.

Response

```
201 Created
```

---

## 2. Update Advocate

**Endpoint**

```
POST /advocate/v1/_update
```

**Description**

Updates an existing advocate application.

Response

```
200 OK
```

---

## 3. Search Advocate

**Endpoint**

```
POST /advocate/v1/_search
```

**Description**

Searches advocate applications based on search criteria.

Response

```
200 OK
```

---

# Project Structure

```
digit_assignment
│
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   │
│   └── test
│
├── build
│   └── digit_assignment.jar
│
├── workflow
│   └── workflow_config.json
│
├── masters
│   ├── advocate-schema.json
│   └── master-data.json
│
├── persister
│   └── persister-config.yaml
│
├── indexer
│   └── indexer-config.yaml
│
├── api_test
│   └── postman_collection.json
│
└── README.md
```

---

# Build Instructions

Clone the repository

```
git clone https://github.com/shwetha-hegde/digit-assignment.git
```

Move to project directory

```
cd digit-assignment
```

Build the project

```
mvn clean install
```

Run the application

```
mvn spring-boot:run
```

---

# Running Unit Tests

Execute

```
mvn test
```

Expected Result

```
Tests run: 3
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

# Postman Collection

The Postman collection for testing all APIs is available under

```
api_test/
```

---

# Workflow Configuration

Workflow configuration JSON is available under

```
workflow/
```

---

# Master Data

Master Data schemas are available under

```
masters/
```

---

# Persister Configuration

Persister configuration YAML is available under

```
persister/
```

---

# Indexer Configuration

Indexer configuration YAML is available under

```
indexer/
```

---

# Database

Database migration scripts are available in

```
src/main/resources/db/migration
```

---

# JAR File

The executable JAR is available in

```
build/digit_assignment.jar
```

---

# Screenshots

The assignment submission includes screenshots of

- Successful Create API
- Successful Update API
- Successful Search API
- JUnit Test Results

---

# Repository

GitHub Repository

https://github.com/shwetha-hegde/digit-assignment

---

# Assignment Deliverables

✔ Source Code

✔ Public GitHub Repository

✔ JAR File

✔ Workflow Configuration

✔ Master Data

✔ Persister Configuration

✔ Indexer Configuration

✔ Postman Collection

✔ JUnit Test Cases

✔ API Testing

---

# Author

**Shwetha Bhaskar Hegde**

Software Developer

DIGIT Developer Certification (Level 1) Assignment
