# Advocate Registration Module - DIGIT Developer Certification

## Introduction

This repository contains the implementation of the **Advocate Registration Module** developed as part of the **DIGIT Developer Certification (Level 1)** assignment.

The application is built using **Java Spring Boot** and follows the recommended DIGIT Backend Developer Guidelines. It provides REST APIs for advocate registration and integrates with the core DIGIT platform services such as IDGen, MDMS, Persister, and Workflow.

---

## Key Functionalities

* Register a new Advocate
* Update Advocate Registration
* Search Advocate Applications
* Workflow Management
* Automatic Application ID Generation
* Master Data Validation
* Data Persistence
* Flyway Database Migration
* Unit Testing
* API Testing with Postman

---

## Tech Stack

| Technology  | Version               |
| ----------- | --------------------- |
| Java        | 17                    |
| Spring Boot | Latest Stable         |
| Maven       | Build Tool            |
| PostgreSQL  | Database              |
| Flyway      | Database Migration    |
| Jackson     | JSON Processing       |
| Lombok      | Boilerplate Reduction |
| JUnit 5     | Unit Testing          |
| Mockito     | Mock Testing          |

---

## DIGIT Service Integrations

The module communicates with the following DIGIT platform services:

| Service                  | Function                                            |
| ------------------------ | --------------------------------------------------- |
| **IDGen**                | Generates unique advocate application IDs           |
| **MDMS**                 | Provides and validates master data                  |
| **Workflow**             | Controls application lifecycle and approval process |
| **Persister**            | Persists application data into the database         |
| **Indexer** *(Optional)* | Enables efficient search and indexing               |

---

## Available APIs

### 1. Create Advocate

**Method:** `POST`

**Endpoint**

```
/advocate/v1/_create
```

**Purpose**

Creates a new advocate registration application.

**Response**

```
HTTP 201 Created
```

---

### 2. Update Advocate

**Method:** `POST`

**Endpoint**

```
/advocate/v1/_update
```

**Purpose**

Updates an existing advocate registration application.

**Response**

```
HTTP 200 OK
```

---

### 3. Search Advocate

**Method:** `POST`

**Endpoint**

```
/advocate/v1/_search
```

**Purpose**

Searches advocate registration records using search criteria.

**Response**

```
HTTP 200 OK
```

---

## Project Directory Structure

```
digit_assignment
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── digit.academy.tutorial
│   │   └── resources
│   │       └── db
│   │           └── migration
│   └── test
│
├── workflow
│   └── workflow_config.json
│
├── masters
│   ├── master_schema_1.json
│   ├── master_schema_2.json
│   └── master_schema_3.json
│
├── persister
│   └── persister_config.yaml
│
├── indexer
│   └── indexer_config.yaml
│
├── api_test
│   ├── postman_collection_1.json
│   └── postman_collection_2.json
│
├── build
│   └── digit_assignment.jar
│
└── README.md
```

---

## Getting Started

### Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

### Navigate to the Project

```bash
cd digit_assignment
```

### Build the Project

```bash
mvn clean install
```

### Start the Application

```bash
mvn spring-boot:run
```

---

## Running Tests

Execute the following command:

```bash
mvn test
```

Expected output:

```text
BUILD SUCCESS
```

---

## Repository Contents

This repository includes:

* Source code
* Workflow configuration
* Master data schemas
* Persister configuration
* Indexer configuration
* Database migration scripts
* Executable JAR
* Postman collections
* Unit test cases
* API execution screenshots

---

## Assignment Deliverables

* ✔ Public GitHub Repository
* ✔ Java Spring Boot Source Code
* ✔ Executable JAR File
* ✔ Workflow JSON Configuration
* ✔ Master Data JSON Schemas
* ✔ Persister YAML Configuration
* ✔ Indexer YAML Configuration
* ✔ Postman Collections
* ✔ JUnit Test Cases
* ✔ API Test Results

---

## Author

**Shama Bhat**

Software Developer

DIGIT Developer Certification (Level 1)
