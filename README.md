![image](https://github.com/user-attachments/assets/8f0edbd0-0d4d-4a2c-b53f-2392c8464386)
![image](https://github.com/user-attachments/assets/efdf5818-bb37-49e3-9e6c-97b1d4f2b5ee)
![image](https://github.com/user-attachments/assets/779fb05c-ac08-4eb1-8048-dfd1897e8929)
![image](https://github.com/user-attachments/assets/254bfcfc-033e-4d4f-a72d-db33b0622ea2)

# Smart Campus Services Portal - README

## Overview

This project is a **Smart Campus Services Portal** developed using **Spring Boot (Java)** for the backend and **React** for the frontend. It includes modules for:

* Student & Lecturer management
* Timetable management
* Consultation appointment scheduling
* Maintenance issue reporting
* Real-time notifications

---

## Backend Stack

* **Spring Boot 3.4.4**
* **MySQL 8** (via JDBC & Spring Data JPA)
* **Hibernate ORM**
* **RESTful API (Spring Web)**

## Frontend Stack

* **React JS**
* **Axios** (API consumption)
* **CSS modules**

---

## Features

### ✅ Students

* View class & consultation timetables
* Book appointments with lecturers
* Receive session notifications

### ✅ Lecturers

* View and manage consultation slots

### ✅ Admins

* Manage maintenance issues
* Oversee notification distribution

---

## Database Schema Script (MySQL)

```sql
 DROP TABLES in order of dependency
DROP TABLE IF EXISTS Notification_Recipient;
DROP TABLE IF EXISTS Notification;
DROP TABLE IF EXISTS MaintenanceIssue;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS Timetable;
DROP TABLE IF EXISTS Module;
DROP TABLE IF EXISTS Lecturer;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Admin;

-- Admin table
CREATE TABLE Admin (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FName VARCHAR(100) NOT NULL,
    LName VARCHAR(100) NOT NULL,
    Password VARCHAR(100) NOT NULL
);

-- Student table
CREATE TABLE Student (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    FName VARCHAR(100) NOT NULL,
    LName VARCHAR(100) NOT NULL,
    Student_Email VARCHAR(100) UNIQUE NOT NULL,
    Contact_No VARCHAR(50) NOT NULL,
    Password VARCHAR(100) NOT NULL
);

-- Lecturer table
CREATE TABLE Lecturer (
    LecturerID INT AUTO_INCREMENT PRIMARY KEY,
    FName VARCHAR(100) NOT NULL,
    LName VARCHAR(100) NOT NULL,
    Lecturer_Email VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL
);

-- Module table
CREATE TABLE Module (
    ModuleID INT AUTO_INCREMENT PRIMARY KEY,
    ModuleName VARCHAR(100) NOT NULL,
    ModuleCode VARCHAR(50) UNIQUE NOT NULL
);

-- Timetable table
CREATE TABLE Timetable (
    TimetableID INT AUTO_INCREMENT PRIMARY KEY,
    LecturerID INT NOT NULL,
    ModuleID INT NOT NULL,
    Day VARCHAR(20) NOT NULL,
    FOREIGN KEY (LecturerID) REFERENCES Lecturer(LecturerID),
    FOREIGN KEY (ModuleID) REFERENCES Module(ModuleID),
    startTime TIME,
    endTime TIME
);

-- Appointment table
CREATE TABLE Appointment (
    AppointmentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentID INT NOT NULL,
    LecturerID INT NOT NULL,
    Date DATE NOT NULL,
    TimeSlot TIME NOT NULL,
    Status VARCHAR(50) NOT NULL,
    ModuleID INT NOT NULL,
    startTime TIME,
    endTime TIME,
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (LecturerID) REFERENCES Lecturer(LecturerID),
    FOREIGN KEY (ModuleID) REFERENCES Module(ModuleID)
);

-- MaintenanceIssue table
CREATE TABLE MaintenanceIssue (
    IssueID INT AUTO_INCREMENT PRIMARY KEY,
    Reported_By INT NOT NULL,
    Managed_By INT,
    Issue_Description TEXT NOT NULL,
    ReportDate DATE NOT NULL,
    Status VARCHAR(50) NOT NULL,
    FOREIGN KEY (Reported_By) REFERENCES Student(StudentID),
    FOREIGN KEY (Managed_By) REFERENCES Admin(AdminID)
);

-- Notification table
CREATE TABLE Notification (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    Message TEXT NOT NULL,
    Date DATE NOT NULL
);

-- Notification_Recipient junction table
CREATE TABLE Notification_Recipient (
    RecipientID INT AUTO_INCREMENT PRIMARY KEY,
    NotificationID INT NOT NULL,
    RecipientType ENUM('student', 'lecturer') NOT NULL,
    StudentID INT,
    LecturerID INT,
    FOREIGN KEY (NotificationID) REFERENCES Notification(NotificationID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID),
    FOREIGN KEY (LecturerID) REFERENCES Lecturer(LecturerID),
    CONSTRAINT CHK_Recipient CHECK (
        (RecipientType = 'student' AND StudentID IS NOT NULL AND LecturerID IS NULL) OR
        (RecipientType = 'lecturer' AND LecturerID IS NOT NULL AND StudentID IS NULL)
    )
);

-- SAMPLE DATA
INSERT INTO Admin (AdminID, FName, LName, Password) VALUES
(1, 'Alice', 'Johnson', 'password123'),
(2, 'Bob', 'Smith', 'password456');

INSERT INTO Student (StudentID, FName, LName, Student_Email, Contact_No, Password) VALUES
(1, 'John', 'Doe', 'johndoe@example.com', '1234567890', 'studentpass1'),
(2, 'Jane', 'Doe', 'janedoe@example.com', '0987654321', 'studentpass2');

INSERT INTO Lecturer (LecturerID, FName, LName, Lecturer_Email, Password) VALUES
(1, 'Dr. David', 'Williams', 'davidwilliams@example.com', 'lecturerpass1'),
(2, 'Prof. Emma', 'Jones', 'emmajones@example.com', 'lecturerpass2');

INSERT INTO Module (ModuleID, ModuleName, ModuleCode) VALUES
(1, 'Computer Science 101', 'CS101'),
(2, 'Mathematics 101', 'MATH101');

INSERT INTO Timetable (TimetableID, LecturerID, ModuleID, Day, startTime, endTime) VALUES
(1, 1, 1, 'Monday', '09:00:00', '10:00:00'),
(2, 2, 2, 'Wednesday', '13:00:00', '14:00:00');

INSERT INTO Appointment (AppointmentID, StudentID, LecturerID, Date, TimeSlot, Status, ModuleID, startTime, endTime) VALUES
(1, 1, 1, '2025-04-24', '10:00:00', 'Scheduled', 1, '10:00:00', '11:00:00'),
(2, 2, 2, '2025-04-25', '14:00:00', 'Cancelled', 2, '14:00:00', '15:00:00');

INSERT INTO MaintenanceIssue (IssueID, Reported_By, Managed_By, Issue_Description, ReportDate, Status) VALUES
(1, 1, 2, 'Broken projector in Room 101', '2025-04-23', 'Open'),
(2, 2, 1, 'Leaking roof in Room 202', '2025-04-22', 'Closed');

INSERT INTO Notification (NotificationID, Message, Date) VALUES
(1, 'Your appointment is scheduled for tomorrow at 10 AM.', '2025-04-23'),
(2, 'Your appointment for the 25th has been cancelled.', '2025-04-23');

INSERT INTO Notification_Recipient (NotificationID, RecipientType, StudentID) VALUES
(1, 'student', 1),
(2, 'student', 2);
```

---

## How to Run

### 1. Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Make sure MySQL is running and the schema above is executed.




---

Feel free to contribute or improve this system!


[Software Engineering Project-Documentation (AutoRecovered).pdf](https://github.com/user-attachments/files/20418085/Software.Engineering.Project-Documentation.AutoRecovered.pdf)

