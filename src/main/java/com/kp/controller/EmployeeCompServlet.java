package com.kp.controller;

import com.kp.dao.ComplaintDao;
import com.kp.model.Complaint;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeCompServlet extends HttpServlet {
     
     private BasicDataSource ds;
     
     @Override
     public void init() throws ServletException {
          ds = (BasicDataSource) getServletContext().getAttribute("ds");
     }
     
     
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String action = req.getParameter("action");
          
          try {
               switch (action) {
                    case "load" -> loadEmployeeComTable(req, resp);
                    case "save" -> saveEmployeeComData(req, resp);
                    case "add" -> {
                         RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp?page=employeeSave");
                         rd.forward(req, resp);
                    }
                    case "clearForm" -> clearEmployeeComData(req, resp);
                    case "edit" -> editEmployeeComData(req, resp);
                    case "update" -> updateEmployeeComData(req, resp);
                    case "delete" -> deleteEmployeeComData(req, resp);
                    default -> resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
               }
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
     }
     
     private void loadEmployeeComTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, ServletException {
          HttpSession session = req.getSession();
          String id = (String) session.getAttribute("userId");
          
          ComplaintDao dao = new ComplaintDao(ds);
          List<Complaint> list = dao.getComplaintOfEmpById(id);
          session.setAttribute("complaintEmpList", list);
          
          resp.sendRedirect("dashboard.jsp?page=employeeView");
     }
     
     private void saveEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          ComplaintDao complaintDao = new ComplaintDao(ds);
          HttpSession session = req.getSession();
          
          String id = complaintDao.getNextComplaintId();
          String employee_id = (String) session.getAttribute("userId");
          String title = req.getParameter("title");
          String description = req.getParameter("description");
          
          if (!id.isEmpty() && !title.isEmpty() && !description.isEmpty()) {
               Complaint complaint = new Complaint(id, employee_id, title, description);
               complaint.setStatus("pending");
               
               if (complaintDao.saveComplaint(complaint)) {
                    resp.sendRedirect("dashboard.jsp?page=employeeView&success=save_ok");
               }else{
                    resp.sendRedirect("dashboard.jsp?page=employeeSave&success=save_failed");
               }
          }else{
               resp.sendRedirect("dashboard.jsp?page=employeeSave&success=save_failed");
          }
     }
     
     private void updateEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          Complaint selected = (Complaint) req.getSession().getAttribute("selectedComplaint");
          
          String id = selected.getId();
          String title = req.getParameter("title");
          String description = req.getParameter("description");
          
          if (id == null || id.isEmpty()) {
               resp.sendRedirect("dashboard.jsp?page=employeeView&error=update_failed");
               return;
          }
          
          if (!title.isEmpty() && !description.isEmpty()){
               ComplaintDao complaintDao = new ComplaintDao(ds);
               Complaint complaint = new Complaint(id,title,description);
               
               if (complaintDao.updateComplaintForEmp(complaint)) {
                    resp.sendRedirect("dashboard.jsp?page=employeeView&success=update_ok");
                    
               } else {
                    resp.sendRedirect("dashboard.jsp?page=employeeView&error=update_failed");
               }
          }
          
     }
     
     private void deleteEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          String id = req.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao(ds);
          if (complaintDao.deleteComplaint(id)) {
               resp.sendRedirect("dashboard.jsp?page=employeeView&success=delete_ok");

          } else {
               resp.sendRedirect("dashboard.jsp?page=employeeView&error=delete_failed");
          }
     }
     
     private void editEmployeeComData(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, SQLException, ClassNotFoundException {
          
          String id = request.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao(ds);
          Complaint selectedComplaint = complaintDao.getComplaintByComId(id);
          request.getSession().setAttribute("selectedComplaint", selectedComplaint);
          
          String userId = (String) request.getSession().getAttribute("userId");
          List<Complaint> complaintList = complaintDao.getComplaintOfEmpById(userId);
          request.getSession().setAttribute("complaintEmpList", complaintList);
          
          request.getRequestDispatcher("dashboard.jsp?page=employeeView").forward(request, response);
     }
     
     private void clearEmployeeComData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
          request.getSession().setAttribute("selectedComplaint", null);
          response.sendRedirect("dashboard.jsp?page=employeeView");
     }
}

