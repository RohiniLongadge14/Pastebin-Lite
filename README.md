# Pastebin-Lite
A lightweight Pastebin-like service built with Spring Boot, supporting TTL and view-based expiration with persistent storage.





PasteBin Clone – Secure Text Sharing Service

A lightweight, scalable, and secure Pastebin-like application built using Spring Boot, designed for sharing text snippets through unique URLs. The application supports Time-To-Live (TTL) expiration, view-based expiration, and persistent database storage, ensuring that shared content is automatically removed based on configured rules.

🚀 Project Overview

This project allows users to create and share text snippets (pastes) instantly. Each paste is assigned a unique identifier and can be accessed through a generated URL. Users can define how long the paste should remain available or limit the number of times it can be viewed.

The application is designed following industry-standard backend development practices using Spring Boot, Spring Data JPA, Hibernate, and RESTful APIs.

✨ Features
📌 Paste Creation
Create and store text snippets securely.
Generate unique URLs for each paste.
Support for large text content.
⏳ Time-Based Expiration (TTL)
Configure paste expiration after a specific duration.
Automatically invalidate and remove expired pastes.
Prevent access to expired content.
👁️ View-Based Expiration
Define the maximum number of views allowed.
Automatically delete or disable access once the view limit is reached.
Useful for sharing sensitive information.
💾 Persistent Storage
Store paste data in a relational database.
Ensure data persistence across application restarts.
Maintain paste metadata such as creation time, expiration settings, and view count.
🔍 Paste Retrieval
Access pastes through unique URLs.
Track remaining views.
Validate expiration conditions before displaying content.
⚡ RESTful API Architecture
Clean REST endpoints for creating and retrieving pastes.
JSON-based request and response handling.
Easy integration with frontend applications.
🛡️ Exception Handling
Centralized exception management.
Meaningful error responses for invalid or expired pastes.
Improved API reliability and user experience.
📊 Metadata Management
Creation timestamp tracking.
Remaining view count tracking.
Expiration status monitoring.
🏗️ Tech Stack
Technology	Purpose
Java 17	Programming Language
Spring Boot	Backend Framework
Spring Data JPA	Data Access Layer
Hibernate	ORM Framework
MySQL/PostgreSQL	Database
Maven	Dependency Management
Lombok	Boilerplate Code Reduction
REST API	Communication Layer
📂 Project Architecture
src/main/java
│
├── controller
│   └── PasteController
│
├── service
│   └── PasteService
│
├── repository
│   └── PasteRepository
│
├── entity
│   └── Paste
│
├── exception
│   ├── ResourceNotFoundException
│   └── GlobalExceptionHandler
│
└── config
    └── ApplicationConfig
🔄 Workflow
Creating a Paste
User submits text content.
System generates a unique identifier.
User optionally specifies:
Expiration time (TTL)
Maximum allowed views
Paste is stored in the database.
Unique shareable URL is returned.
Accessing a Paste
User opens the generated URL.
System checks:
Whether the paste exists.
Whether TTL has expired.
Whether the view limit is exceeded.
If valid:
Display content.
Increment view count.
If invalid:
Return appropriate error response.
📌 Sample API Endpoints
Create Paste
POST /api/pastes

Request Body:

{
  "content": "Hello World!",
  "expirationMinutes": 60,
  "maxViews": 10
}

Response:

{
  "pasteId": "abc123",
  "url": "/api/pastes/abc123"
}
Get Paste
GET /api/pastes/{pasteId}

Response:

{
  "content": "Hello World!",
  "remainingViews": 9
}
🔒 Security Considerations
Unique random paste identifiers.
Validation of input data.
Protection against invalid requests.
Automatic cleanup of expired content.
Centralized error handling.
🎯 Learning Outcomes

This project demonstrates practical implementation of:

Spring Boot REST APIs
CRUD Operations
Database Integration using JPA & Hibernate
Entity Relationships and Persistence
Exception Handling
TTL-Based Data Management
View Count Tracking
Service Layer Architecture
Clean Code Principles
Backend System Design
🌟 Future Enhancements
User Authentication & Authorization
Password-Protected Pastes
Encrypted Content Storage
Syntax Highlighting for Code Snippets
File Upload Support
Paste Editing and Versioning
Scheduled Cleanup Jobs
Redis Caching for Performance Optimization
Docker & Kubernetes Deployment
📜 Conclusion

The PasteBin Clone is a robust backend application that showcases modern Spring Boot development practices. By combining time-based expiration, view-based access control, and persistent storage, it provides a secure and efficient platform for temporary text sharing while serving as an excellent learning project for backend developers.

## Author
Rohini Anilrao Longadge
