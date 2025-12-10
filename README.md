# Smart Summarize – AI Text Summarization API

**Smart Summarize** is a modern, backend-focused text summarization API built with **Spring Boot** and powered by **Hugging Face** transformer models.

It exposes clean REST endpoints that allow clients to:
- Submit long text
- Choose the desired summary length (short / medium / long)
- Receive a concise AI-generated summary
- Retrieve previously created summaries from a PostgreSQL database

This project is designed as a **portfolio-quality backend service** that demonstrates:
- Clean architecture
- Real-world integration with external AI APIs
- Database persistence
- Production-friendly structure

---

## Features

- **AI-powered text summarization** using Hugging Face Inference API  
- **Configurable summary length** (SHORT / MEDIUM / LONG)  
- **PostgreSQL persistence** for summaries (history & analytics)  
- **Clean layered architecture**:
  - `api` (controllers, DTOs, exception handling)
  - `application` (services / use cases)
  - `domain` (entities, models, repositories)
  - `infrastructure` (Hugging Face client, config)
- **RESTful API** with JSON input/output  
- **Input validation & basic error handling**  
- Ready to be consumed by frontend (React, Angular, mobile, etc.)

---

## Architecture Overview

The project follows a **modular monolith** / **clean architecture** style.

```text
com.imad.smartSummarize
│
├── api
│   ├── controller         # REST controllers (SummaryController)
│   ├── dto                # Request/Response DTOs
│   └── exception          # Global exception handling
│
├── application
│   └── service            # Business logic / use cases (SummaryService)
│
├── domain
│   ├── entity             # JPA entities (Summary)
│   ├── model              # Domain models/enums (SummaryLength)
│   └── repository         # Repository interfaces (SummaryRepository)
│
├── infrastructure
│   ├── config             # Technical configuration (WebClientConfig, etc.)
│   └── hf                 # Hugging Face API client (HuggingFaceClient)
│
└── SmartSummarizeApplication.java  # Spring Boot main entry point
