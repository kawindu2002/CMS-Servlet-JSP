package com.kp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet {
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          HttpSession session = req.getSession();
          session.removeAttribute("id");
          session.removeAttribute("name");
          session.removeAttribute("email");
          session.removeAttribute("role");
          session.removeAttribute("password");
          session.invalidate();
          resp.sendRedirect("signInPage.jsp");
          
     }
}

