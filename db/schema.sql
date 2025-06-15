CREATE DATABASE CMS;

USE CMS;

CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       role ENUM('admin', 'employee') NOT NULL,
       password VARCHAR(255) NOT NULL
);

CREATE TABLE complaints (
        id INT PRIMARY KEY AUTO_INCREMENT,
        employee_id INT NOT NULL,
        title VARCHAR(100) NOT NULL,
        description TEXT NOT NULL,
        status VARCHAR(20) NOT NULL DEFAULT 'pending',
        admin_remark TEXT,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        FOREIGN KEY (employee_id) REFERENCES users(id)
);

CREATE TRIGGER update_complaint_time
    BEFORE UPDATE ON complaints
    FOR EACH ROW
    SET NEW.updated_at = NOW();

