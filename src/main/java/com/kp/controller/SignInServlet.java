package com.kp.controller;

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
          
//          LoginDao loginDao = new LoginDao();

//          if (loginDao.checkLogin(username,password)) {
          
          if (email.equals("kp@gmail.com") && password.equals("1111")) {
               HttpSession session = req.getSession();
               session.setAttribute("email", email);
               session.setAttribute("password", password);
               System.out.println("Success");
               resp.sendRedirect("dashboard.jsp");
               
          }else{
               resp.sendRedirect("signInPage.jsp");
          }
          
     }
}

