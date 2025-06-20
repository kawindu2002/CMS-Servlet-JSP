# 🖥️ JSP-Based Complaint Management System (CMS)

Welcome to **CMS-Servlet-JSP**, a web-based Complaint Management System built using **JSP**, **Servlets**, and **MySQL** following the **MVC architecture**.  
This project is a part of my academic submission for the **IJSE GDSE program**, focused on understanding how web systems handle structured data flow and user interactions without external frameworks.

---

## 🚀 Features

- 📝 **Complaint Submission**: Users can submit complaints with title, description, and category.
- 🧑‍💼 **Admin Panel**: Admins can view and update complaint statuses.
- 🔐 **Authentication**: Basic user login system with session management.
- 📊 **Dashboard View**: Role-based dashboards for users and admins.
- 🚫 **Cache Control**: Prevents back navigation after logout.
- 🖥️ **JSP + Servlet Integration**: MVC-based flow with form handling and DB communication.

---

## 🛠️ Tech Stack

| Layer        | Technology                           |
|--------------|--------------------------------------|
| Frontend     | HTML, CSS, JSP (Only for validation) |
| Backend      | Java Servlets, JDBC                  |
| Database     | MySQL                                |
| Build Tool   | Apache Tomcat (WAR)                  |
| Database Pool| Custom DBConnectionPool              |
| Architecture | MVC (Model View Controller)          |

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
   git clone https://github.com/kawindu2002/CMS-Servlet-JSP.git
   cd CMS-Servlet-JSP

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


