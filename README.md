# 🖥️ JSP-Based Complaint Management System (CMS)

A full-stack web application built using **Java (JSP/Servlets)**, **JDBC**, and **MySQL**, designed to manage user complaints and admin responses in an efficient and organized manner.

---

## 🚀 Features

### 👤 User Panel:
- Register and log in
- Submit complaints (with subject & message)
- Track the status of submitted complaints
- View answers from the admin

### 🛠️ Admin Panel:
- View all submitted complaints
- Respond to complaints with answers
- Manage users and complaint statuses
- Track solved, pending, and weekly statistics

### 📊 Dashboard:
- Dynamic overview of pending vs solved complaints
- Easy navigation for both users and admins

---

## 🛠️ Tech Stack

| Layer        | Technology              |
|--------------|--------------------------|
| Frontend     | HTML, CSS, JSP           |
| Backend      | Java Servlets, JDBC      |
| Database     | MySQL                    |
| Build Tool   | Apache Tomcat (WAR)      |
| Database Pool| Custom DBConnectionPool  |
| Architecture | MVC (Model View Controller) |

---

## 📂 Project Structure

```
CMS-Servlet-JSP/
├── /src/
│   ├── com.kp.controller       # Java Servlets (Controllers)
│   ├── com.kp.dao              # DAO classes for DB interaction
│   ├── com.kp.model            # POJOs (JavaBeans)
│   └── com.kp.util             # DB utilities
├── /web/
│   ├── /pages                  # JSP files (User & Admin views)
│   ├── /assets                 # CSS, JS, images
│   └── WEB-INF/web.xml         # Deployment descriptor
├── /db/
│   └── schema.sql              # SQL dump (ready to import)
└── README.md                   # You’re here!

```


---

## 🧑‍💻 How to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/Dinuka0512/Complaint-Management-System-CMS.git
  
## ✅ To-Do (Optional Enhancements)

- [ ] 🔐 Add login authentication with password hashing
- [ ] 📧 Add email notifications for complaint updates
- [ ] 📤 Export reports (PDF/CSV)
- [ ] 🌐 Add RESTful API support
- [ ] 📱 Responsive mobile layout

---

## 🙌 Acknowledgements

- 💻 Java EE Servlet API  
- 🐱 Apache Tomcat  
- 🐬 MySQL  
- 🧠 StackOverflow & Developer Community ❤️  

---

## 📬 Contact

**Developer:** Dinuka Lakmal  
**GitHub:** [Dinuka Lakmal](https://github.com/Dinuka0512)  
**Email:** Dinuka0512@gmail.com 


