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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String name = req.getParameter("name");
          String email = req.getParameter("email");
          String role = req.getParameter("role");
          String password = req.getParameter("password");
          
          UserDao userDao = new UserDao();
          User users = userDao.getUser(email);
          
          if (users.getEmail().equals(email)) {
               System.out.println("This email is already in use");
               resp.sendRedirect("signUpPage.jsp");
               
          }else{
               User user = new User(name,email,role,password);
               if (userDao.saveUser(user)) {
                    resp.sendRedirect("signInPage.jsp?login=success");
                    
               }else{
                    resp.sendRedirect("signUpPage.jsp");
               }
          }
     }
}

