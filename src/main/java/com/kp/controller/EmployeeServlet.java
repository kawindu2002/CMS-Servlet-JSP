package com.kp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
     
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          super.doGet(req, resp);
     }
     
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String method = req.getParameter("_method");
          
          if (method == null || method.equalsIgnoreCase("post")) {
               
          } else if (method.equalsIgnoreCase("put")) {
               doPut(req, resp);
          } else if (method.equalsIgnoreCase("delete")) {
               
//               doDelete(req, resp);
          } else {
               
               resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported method");
          }
     }
     
     @Override
     protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          int id = Integer.parseInt(req.getParameter("id"));
          String title = req.getParameter("title");
          String description = req.getParameter("description");
          String status = req.getParameter("status");
          
          
     }
     
     @Override
     protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          super.doDelete(req, resp);
     }
}


