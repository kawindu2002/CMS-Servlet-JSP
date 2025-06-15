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

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeCompServlet extends HttpServlet {
     
     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String action = req.getParameter("action");
          if ("load".equals(action)) {
               try {
                    loadEmployeeComTable(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("add".equals(action)) {
               RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp?page=employeeSave");
               rd.forward(req, resp);
          }else if ("save".equals(action)) {
               saveEmployeeComData(req, resp);
          }else if ("update".equals(action)) {
               updateEmployeeComData(req, resp);
          }else if ("delete".equals(action)) {
               deleteEmployeeComData(req, resp);
          }else {
               resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
          }
     }
     
     
     private void loadEmployeeComTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          HttpSession session = req.getSession();
          String id = (String) session.getAttribute("id");
          
          ComplaintDao dao = new ComplaintDao();
          List<Complaint> list = dao.getComplaintOfEmpById(id);
          session.setAttribute("complaintEmpList", list);
          
          resp.sendRedirect("dashboard.jsp?page=employeeView");
     }
     
     private void saveEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
          
//          HttpSession session = req.getSession();
//               int employee_id = (int) session.getAttribute("employee_id");
//               String title = req.getParameter("title");
//               String description = req.getParameter("description");
//
//          ComplaintDao complaintDao = new ComplaintDao();
//          Complaint complaint = new Complaint(employee_id,title,description);
//          if (complaintDao.saveComplaint(complaint)) {
//               resp.sendRedirect("dashboard.jsp?page=employeeView&success=save_ok");
//          }else{
//               resp.sendRedirect("dashboard.jsp?page=employeeView&success=save_failed");
//          }
          
     }
     
     
     private void updateEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//          HttpSession session = req.getSession();
//               int employee_id = (int) session.getAttribute("employee_id");
//               int id = Integer.parseInt(req.getParameter("id"));
//               String title = req.getParameter("title");
//               String description = req.getParameter("description");
//               String status = req.getParameter("status");
//               String admin_remark = req.getParameter("admin_remark");
//
//          ComplaintDao complaintDao = new ComplaintDao();
//          Complaint complaint = new Complaint();
//               complaint.setId(id);
//               complaint.setEmployee_id(employee_id);
//               complaint.setTitle(title);
//               complaint.setDescription(description);
//               complaint.setStatus(status);
//               complaint.setRemark(admin_remark);
//
//          if (complaintDao.updateComplaint(complaint)) {
//               resp.sendRedirect("dashboard.jsp?page=employeeView&success=update_ok");
//
//          } else {
//               resp.sendRedirect("dashboard.jsp?page=employee&error=update_failed");
//          }
     }
     
     private void deleteEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//          int id = Integer.parseInt(req.getParameter("id")); //
//          ComplaintDao complaintDao = new ComplaintDao();
//          HttpSession session = req.getSession();
//          if (complaintDao.deleteComplaint(id)) {
//               resp.sendRedirect("dashboard.jsp?page=employeeView&success=delete_ok");
//
//          } else {
//               resp.sendRedirect("dashboard.jsp?page=employee&error=delete_failed");
//          }
     }
     
}

