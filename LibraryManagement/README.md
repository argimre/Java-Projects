# Library Management System

A console-based Java application designed to manage books, customers, and their transactions in a library. This system allows librarians to perform various tasks such as adding/removing books, searching books and customers, checking in and checking out books for customers, and viewing catalogs of books and customers.

## Features
- Book Management:
    - Add new books to the catalog.
    - Remove books from the catalog.
    - Search for books in the catalog.
    - View the list of books in the catalog.

- Customer Management:
    - Add new customers to the catalog.
    - Remove customers from the catalog.
    - Search for customers in the catalog.
    - View the list of customers in the catalog.

- Book Transactions:
    - Checkout books to customers.
    - Check in books from customers..

## Project Structure
```
LibraryManagementSystem/
├── Book.java               # Defines the Book class with attributes like title, author, and genre
├── BookCatalog.java        # Manages the catalog of books in the library
├── Customer.java           # Defines the Customer class with attributes like name, email, and phone
├── CustomerCatalog.java    # Manages the catalog of customers
├── Librarian.java          # Contains methods for librarian actions (add, remove, search, etc.)
├── Order.java              # Represents an order (book checkout or check-in) for a customer
├── LibraryManagement.java  # Main class with the console interface and program logic
└── README.md               # This file
```

## Usage
Upon running the program, the user is presented with the following menu:
```
Library Management System
1 : Add Book
2 : Search Book
3 : List Books
4 : Add Customer
5 : Search Customer
6 : List Customers
7 : Customer Checkin
8 : Customer Checkout
9 : Exit
```
You can choose an option by entering the corresponding number. The system will ask for the necessary details (such as the book title, customer name, etc.) based on the selected option.

#### Example Operations:
 - Add Book: Allows the librarian to add a new book to the catalog by providing the book’s title, author, and genre.
 - Search Book: Searches the catalog for a specific book by title.
 - Add Customer: Allows the librarian to add a new customer with their name, email, phone number, and a unique ID.
 - Customer Checkout: Allows the librarian to check out a book to a customer.
 - Customer Checkin: Allows the librarian to check in a book that was previously checked out by a customer.

## Code Explanation
- Book Class: Represents a book in the library with properties like title, author, and genre.
- BookCatalog Class: Manages a collection of books, allowing for adding, removing, and searching books.
- Customer Class: Represents a customer with properties like name, email, phone, and a list of past orders.
- CustomerCatalog Class: Manages a collection of customers, allowing for adding, removing, and searching customers.
- Order Class: Represents an order where a customer checks out or checks in a book. It records the book and the date of the transaction.
- Librarian Class: Contains methods to manage both the book and customer catalogs, and to process book transactions (checkout and check-in).
- LibraryManagement Class: The main class that runs the program, displaying a menu and interacting with the user.