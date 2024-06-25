# Atm-simulation-project
Overview
This project is a simple ATM simulation written in Java. It demonstrates basic concepts of object-oriented programming, including classes, objects, inheritance, and encapsulation. The ATM simulation allows users to create accounts, log in, and perform various banking transactions such as viewing transaction history, making deposits, withdrawals, and transfers.

Features
User account creation with unique ID and secure PIN
Multiple account types (savings and checking)
Login functionality
Viewing account transaction history
Depositing funds
Withdrawing funds
Transferring funds between accounts
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher
A Java IDE or a text editor
Command line interface for compiling and running the application

User created with ID: 123456
User pin is: 1234

Welcome to Bank of Dayspring

Enter user ID: 123456
Enter pin: 1234
Welcome Dayspring, what would you like to do?
 1) Show account transaction history
 2) Withdrawal
 3) Deposit
 4) Transfer
 5) Quit
Enter choice:
Project Structure
The project consists of the following classes:

ATM: The main class that runs the ATM simulation.
Bank: Manages users and accounts, and provides methods for creating unique IDs.
User: Represents a bank customer with attributes such as name, UUID, and a list of accounts.
Account: Represents a bank account with attributes such as name, UUID, and a list of transactions.
Transaction: Represents a transaction with attributes such as amount, timestamp, and memo.
Code Description
ATM.java
Contains the main method that initializes the bank, creates a user, and starts the ATM simulation loop. It includes methods for the main menu prompt and user menu interactions.

Bank.java
Manages the creation of users and accounts. It generates unique IDs for users and accounts and provides methods for user authentication and account management.

User.java
Represents a user with personal information, hashed PIN, and a list of accounts. It includes methods for validating PINs, adding accounts, and printing account summaries.

Account.java
Represents an account with a name, unique ID, and a list of transactions. It includes methods for managing transactions and getting account information.

Transaction.java
Represents a transaction with an amount, timestamp, memo, and the account it belongs to. It includes constructors for creating transactions with or without memos.

Future Improvements
Implement a graphical user interface (GUI) for better user interaction
Add more account types and features such as loan accounts
Enhance security measures for PIN handling and user authentication
Implement persistent storage for user and account data
