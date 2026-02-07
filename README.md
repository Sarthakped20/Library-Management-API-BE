📚 Library Management System (Spring Boot)
📌 Project Description

This project is a Library Management System developed using Java and Spring Boot.
It provides REST APIs to manage books, users, and book issue/return operations in a library.
The system focuses on backend logic, database interaction, and clean API design.

🚀 Features Implemented
🔹 User Management

Create and manage users

Role-based users (Admin / Normal User)

User details stored in database

🔹 Book Management

Add new books

Update book details

Delete books

Track total and available copies of books

🔹 Book Issue & Return

Issue books to users based on availability

Store issue date and due date

Return issued books

Maintain issue history using a returned flag instead of deleting records

🔹 Fine Calculation

Fine is calculated if the book is returned after the due date

Uses Java 8 Date-Time API (LocalDate, ChronoUnit)

Fine calculation is based on number of overdue days

🔹 Exception Handling

Custom exceptions for invalid operations (e.g., resource not found)

Global exception handling using @RestControllerAdvice

🛠 Tech Stack

Language: Java

Framework: Spring Boot

Database: MySQL (or any relational DB)

ORM: Spring Data JPA (Hibernate)

Security: Spring Security (basic authentication / role-based access)

Build Tool: Maven

API Testing: Postman

🗄 Database Design
Entities Used:

User

Book

IssuedBook

Role

Relationships:

A User can issue multiple books

A Book can be issued multiple times

IssuedBook acts as a mapping entity to store issue and return details

🔁 Application Flow

Admin adds books to the system

User requests to issue a book

System checks book availability

Issue details (issue date, due date) are stored

On return, the system:

Marks the book as returned

Calculates fine if overdue

Updates available book copies

⚠️ Current Limitations

No frontend (backend-only project)

Limited validations

Basic security implementation

No pagination or advanced search

🌱 Future Enhancements

Pagination and filtering for books

Email notifications for due dates

Advanced role-based access

Frontend integration

Improved validations and testing


Basic Spring Security

Real-world backend application flow
