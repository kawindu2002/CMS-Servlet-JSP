package com.kp.controller;

import com.kp.dao.UserDao;
import com.kp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
     private BasicDataSource ds;
     
     @Override
     public void init() throws ServletException {
          ds = (BasicDataSource) getServletContext().getAttribute("ds");
     }
     
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          UserDao dao = new UserDao(ds);
          
          String id = null;
          try {
               id = dao.getNextUserId();
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
          String name = req.getParameter("name");
          String email = req.getParameter("email");
          String role = req.getParameter("role");
          String password = req.getParameter("password");
          
          User user = new User(id,name,email,role,password);
          try {
               if (dao.saveUser(user)) {
                    resp.sendRedirect("signInPage.jsp?login=success");
                    
               }else{
                    resp.sendRedirect("signUpPage.jsp");
               }
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
     }
}

