# **Library Management System API**

This is a Spring Boot-based backend API for managing a library system. 
The system includes functionality for managing books, authors, users, and borrowing/returning books. 
It supports the following features:

* Add books and authors.  
* Borrow books.   
* Return books.   
* Track borrowing records for each user.

Features:
    
* Book Management: Add, view, and manage books with their respective authors.     
* Author Management: Add new authors, or associate existing authors with books.   
* User Management: Register users and manage their book borrow/return history.    
* Borrow Books: Users can borrow books, and the system tracks borrow date and available copies.   
* Return Books: Users can return borrowed books, and the system updates the inventory.

Technologies Used:

* Java: Programming language.
* Spring Boot: Framework for building the backend application.
* JPA/Hibernate: For interacting with the database.
* H2/MySQL: Database (depending on your configuration).
* Maven: Dependency management and build tool.