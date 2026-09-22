# HelpHands Mail

### A Microservice for Email Notifications in the HelpHands Platform

HelpHands Mail is a **Java-based REST microservice** developed to provide email notification functionality for the main **[HelpHands](https://github.com/stefLind/HelpHands)** application, a platform for donating manual labor, services, food, and other forms of assistance.

The microservice is responsible for sending email notifications to users participating in campaigns when relevant campaign changes occur. It also manages and stores the minimum user information required for delivering these notifications.

HelpHands Mail is designed as an independent service that communicates with the main HelpHands application through **REST APIs**, allowing email-related functionality to be separated from the core application.

---

## 📖 About the Project

HelpHands Mail provides email notification functionality for the HelpHands platform.

The microservice:

* Receives an already-prepared email content from the main HelpHands application
* Sends email notifications to participating users
* Receives and stores limited user information required for sending notifications
* Updates stored user information when users are modified in the main application

The separation of email functionality into an independent microservice allows the main HelpHands application to delegate notification-related operations to a dedicated service.

---

## 🔄 Communication with HelpHands

HelpHands Mail communicates with the main **HelpHands** application through RESTful APIs.

A simplified communication flow is:

```text
┌──────────────────────┐
│      HelpHands       │
│   Main Application   │
└──────────┬───────────┘
           │
           │ REST API
           │
           ▼
┌──────────────────────┐
│    HelpHands Mail    │
│     Microservice     │
└──────────┬───────────┘
           │
           │ Email
           ▼
      ┌───────────┐
      │   Users   │
      └───────────┘
```

When a campaign is modified:

1. The campaign is modified in the **HelpHands** application.
2. HelpHands determines which users should be notified.
3. HelpHands constructs the email subject and body containing the campaign changes.
4. The prepared notification information is sent to **HelpHands Mail** through a REST API.
5. HelpHands Mail uses the stored user ID and email address to identify the recipients.
6. HelpHands Mail sends the email notification to the users.

The responsibility for creating the notification content therefore remains within the main HelpHands application, while email delivery is handled by the HelpHands Mail microservice.

---

## ✨ Features

### 📧 Campaign Email Notifications

* Receives prepared email notifications from the HelpHands application
* Sends campaign-related email notifications to users
* Uses email content prepared by the main HelpHands application
* Separates email delivery from the main application's business logic

---

### 👤 User Information Management

HelpHands Mail stores only the minimum user information required for email notification delivery:

* User ID
* Email address

The microservice can receive user information when a user is created or when their email address is modified in the main HelpHands application.

---

### 🔗 REST Communication

HelpHands Mail exposes REST endpoints used by the main HelpHands application for communication.

The REST API is used for operations such as:

* Receiving prepared email notification data
* Creating user notification records
* Updating user information
* Triggering email notifications

---

## 🛠 Tech Stack

HelpHands Mail is a **Spring Boot-based REST microservice** written in Java 21. It uses MySQL for persistent data storage and is designed as an independent component of the HelpHands platform.

---

## 🔙 Backend

* **Java 21** – Core programming language
* **Spring Boot 4.0.6** – Framework for rapid application development

### Spring Boot Modules & Starters

* **Spring Web** – RESTful API development and HTTP request handling
* **Spring Data JPA** – Database access and persistence using Java entities
* **Spring Validation** – Input validation for requests and DTOs
* **Spring Framework Mail support** – Integration with email services and email message delivery

### Additional Libraries

* **Lombok** – Reduces boilerplate code such as getters, setters, constructors, and other commonly generated methods

---

## 🗄 Database

* **MySQL** – Relational database used for persistent storage
* **Hibernate** – ORM framework used for database persistence through JPA

---

## 📦 Build & Dependency Management

* **Maven** – Project build and dependency management tool
* **Spring Boot Maven Plugin** – Packaging and running the application with Spring Boot support

---

## 🔌 Integration

HelpHands Mail is intended to operate as a supporting microservice of the HelpHands application.

The main HelpHands application is responsible for the core platform functionality, while HelpHands Mail handles email notification-related operations.

This separation provides a clear responsibility boundary:

| Application        | Responsibility                                                                 |
| ------------------ | ------------------------------------------------------------------------------ |
| **HelpHands**      | Users, campaigns, applications, participation, and core platform functionality |
| **HelpHands Mail** | User notification data and email notification delivery                         |

---

## 🎓 Purpose

This microservice is developed as part of a **Master’s degree thesis** and demonstrates the practical implementation of a supporting microservice within a larger Spring-based web application.

The project demonstrates:

* RESTful microservice development
* Communication between independent Spring Boot applications
* Separation of responsibilities between application components
* Database persistence using Spring Data JPA and MySQL
* Email integration using Spring Framework Mail support
* Microservice-oriented application architecture
* Independent service development using Java and Spring Boot

---

## 📌 Related Project

**HelpHands – Main Application**

https://github.com/stefLind/HelpHands

HelpHands Mail is designed to communicate with the main HelpHands application and provide its email notification functionality.

---

## 📄 License

This project is intended for **educational use only**.
