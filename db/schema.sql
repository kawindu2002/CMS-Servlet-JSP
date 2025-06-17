Drop database CMS_IJSE;
CREATE DATABASE CMS_IJSE;

USE CMS_IJSE;

CREATE TABLE users (
       id VARCHAR(20) PRIMARY KEY ,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       role VARCHAR(100) NOT NULL,
       password VARCHAR(255) NOT NULL
);

CREATE TABLE complaints (
        id VARCHAR(20) PRIMARY KEY ,
        employee_id VARCHAR(20) NOT NULL,
        title VARCHAR(100) NOT NULL,
        description TEXT NOT NULL,
        status VARCHAR(20) NOT NULL,
        admin_remark VARCHAR(255) DEFAULT 'not added',
        FOREIGN KEY (employee_id) REFERENCES users(id)
);


INSERT INTO users (id, name, email, role, password) VALUES
    ('U001', 'Alice', 'alice@example.com', 'employee', '1111'),
    ('U002', 'Bob', 'bob@example.com', 'employee', '2222'),
    ('U003', 'Charlie', 'charlie@example.com', 'admin', '3333');


INSERT INTO complaints (id, employee_id, title, description, status, admin_remark) VALUES
   ('C001', 'U001', 'Internet Down', 'No internet access on 3rd floor.', 'pending', 'not added'),
   ('C002', 'U002', 'Broken AC', 'AC leaking water in conference room.', 'in progress', 'Technician scheduled'),
   ('C003', 'U001', 'Printer Jam', 'Printer keeps jamming papers frequently.', 'resolved', 'Replaced printer rollers');


