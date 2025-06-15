CREATE DATABASE CMS;

USE CMS;

CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       role ENUM('admin', 'employee') NOT NULL,
       password VARCHAR(255) NOT NULL
);


