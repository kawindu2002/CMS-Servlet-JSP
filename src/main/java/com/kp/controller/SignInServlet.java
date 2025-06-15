package com.kp.controller;

import com.kp.dao.UserDao;
import com.kp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String email = req.getParameter("email");
          String password = req.getParameter("password");
          
          UserDao userDao = new UserDao();
          User user = null;
          try {
               user = userDao.findByEmail(email);
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
          
          if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                HttpSession session = req.getSession();
               session.setAttribute("id", user.getId());
               session.setAttribute("name", user.getName());
               session.setAttribute("email", user.getEmail());
               session.setAttribute("role", user.getRole());
               session.setAttribute("password", user.getPassword());
               
               if (user.getRole().equals("admin")) {
                    resp.sendRedirect("dashboard.jsp?page=adminView");
               }else if (user.getRole().equals("employee")){
                    resp.sendRedirect("dashboard.jsp?page=employeeView");
               }
               
          }else{
               resp.sendRedirect("signInPage.jsp");
          }
     }
}


