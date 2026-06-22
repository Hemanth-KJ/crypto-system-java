# Crypto System – Java File Encryption & Decryption

## Overview

Crypto System is a desktop-based file encryption and decryption application developed using Java, Swing UI, JDBC, and MySQL.

The system allows users to register, login, encrypt files, decrypt encrypted files using a password, maintain history logs, and manage users through an admin dashboard.

The application uses a single-window tab-based interface.

---

## Features

### Authentication

* User Registration
* User Login
* Role-based Access (User / Admin)
* Logout Functionality

### File Encryption

* AES Encryption Algorithm
* Encrypt Files from File Explorer
* Password Protected Encryption
* Store File Information in Database

### File Decryption

* Password Verification
* Restore Encrypted Files
* Store Decryption Path

### Admin Dashboard

* View Registered Users
* Delete Users
* View Total Users
* View Total Files
* View Total History Records
* Refresh Dashboard

### History Module

* Track Encryption Activities
* Track Decryption Activities
* Store User Actions
* Refresh History Records

### User Interface

* Java Swing Interface
* Single Window Application
* Tab Navigation

---

## Technologies Used

* Java
* Java Swing
* NetBeans IDE
* JDBC
* MySQL
* MySQL Workbench
* AES Cryptography
* Git
* GitHub

---

## Project Structure

```plaintext
crypto-system-java/

├── src/
│
│   ├── Main.java
│
│   ├── crypto/
│   │     └── AESUtil.java
│
│   ├── database/
│   │     └── DBConnection.java
│
│   ├── service/
│   │     ├── AuthService.java
│   │     ├── CryptoService.java
│   │     ├── AdminService.java
│   │     └── HistoryService.java
│
│   └── ui/
│         └── MainUI.java
│
├── build/
├── dist/
├── nbproject/
│
├── database.sql
├── README.md
└── .gitignore
```

---

## Module Description

### Main.java

Entry point of the application.

### crypto

Contains AES encryption and decryption logic.

### database

Handles MySQL database connectivity.

### service

Contains business logic:

* Authentication
* File Encryption
* File Decryption
* Admin Operations
* History Management

### ui

Contains application interface.

---

## Database Tables

### users

| Column   | Type    |
| -------- | ------- |
| id       | INT     |
| username | VARCHAR |
| password | VARCHAR |
| role     | VARCHAR |

### files

| Column         | Type      |
| -------------- | --------- |
| id             | INT       |
| filename       | VARCHAR   |
| encrypted_path | TEXT      |
| secret_key     | VARCHAR   |
| decrypted_path | TEXT      |
| created_at     | TIMESTAMP |

### history

| Column      | Type      |
| ----------- | --------- |
| id          | INT       |
| user_id     | INT       |
| action_type | VARCHAR   |
| file_name   | VARCHAR   |
| created_at  | TIMESTAMP |

---

## Application Workflow

```plaintext
Register
↓

Login
↓

Select File
↓

Enter Password
↓

Encrypt File
↓

Store Details in MySQL
↓

Decrypt Using Password
↓

Store History
```

---

## Installation

### Clone Repository

```bash
git clone https://github.com/Hemanth-KJ/crypto-system-java.git
```

---

## Open Project

Open the project in:

```plaintext
NetBeans IDE
```

---

## Configure Database

Open MySQL Workbench.

Run:

```sql
SOURCE database.sql;
```

Update database credentials inside:

```plaintext
database/DBConnection.java
```

---

## Run Application

Run:

```plaintext
src/Main.java
```

or

```plaintext
ui/MainUI.java
```

---

## Git Commands

Push project:

```bash
git add .

git commit -m "Initial project upload"

git push
```

Update project:

```bash
git add .

git commit -m "Project update"

git push
```

---

## Future Improvements

* Stronger encryption methods
* Search and filtering
* Export history
* Cloud integration
* File sharing support

---

## Author

Hemanth K J
