package com.kp.controller;

import com.kp.dao.ComplaintDao;
import com.kp.model.Complaint;
import com.kp.util.CrudUtil;
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
               try {
                    saveEmployeeComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("clearForm".equals(action)) {
               try {
                    clearEmployeeComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("edit".equals(action)) {
               try {
                    editEmployeeComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          } else if ("update".equals(action)) {
               try {
                    updateEmployeeComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("delete".equals(action)) {
               try {
                    deleteEmployeeComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else {
               resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
          }
     }
     
     private void loadEmployeeComTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          HttpSession session = req.getSession();
          String id = (String) session.getAttribute("userId");
          
          ComplaintDao dao = new ComplaintDao();
          List<Complaint> list = dao.getComplaintOfEmpById(id);
          session.setAttribute("complaintEmpList", list);
          
          resp.sendRedirect("dashboard.jsp?page=employeeView");
     }
     
     private void saveEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          ComplaintDao complaintDao = new ComplaintDao();
          HttpSession session = req.getSession();
          
               String id = complaintDao.getNextComplaintId();
               String employee_id = (String) session.getAttribute("userId");
               String title = req.getParameter("title");
               String description = req.getParameter("description");

          Complaint complaint = new Complaint(id,employee_id,title,description);
          if (complaintDao.saveComplaint(complaint)) {
               resp.sendRedirect("dashboard.jsp?page=employeeView&success=save_ok");
          }else{
               resp.sendRedirect("dashboard.jsp?page=employeeView&success=save_failed");
          }
     }
     
     
     private void updateEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          String id = (String) req.getSession().getAttribute("editingComplaintId");
          String title = req.getParameter("title");
          String description = req.getParameter("description");

          ComplaintDao complaintDao = new ComplaintDao();
          Complaint complaint = new Complaint(id,title,description);

          if (complaintDao.updateComplaintForEmp(complaint)) {
               resp.sendRedirect("dashboard.jsp?page=employeeView&success=update_ok");

          } else {
               resp.sendRedirect("dashboard.jsp?page=employeeView&error=update_failed");
          }
     }
     
     private void deleteEmployeeComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          String id = req.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao();
          if (complaintDao.deleteComplaint(id)) {
               resp.sendRedirect("dashboard.jsp?page=employeeView&success=delete_ok");

          } else {
               resp.sendRedirect("dashboard.jsp?page=employeeView&error=delete_failed");
          }
     }
     
     private void editEmployeeComData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
          
          String id = request.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao();
          HttpSession session = request.getSession();
          
          Complaint selectedComplaint = complaintDao.getComplaintByComId(id);
          request.setAttribute("selectedComplaint", selectedComplaint);
          
          // set ID into session (for future update)
          request.getSession().setAttribute("editingComplaintId", id);
          
          String userId = (String) session.getAttribute("userId");
          List<Complaint> complaintList = complaintDao.getComplaintOfEmpById(userId);
          session.setAttribute("complaintEmpList", complaintList);
          
          request.getRequestDispatcher("dashboard.jsp?page=employeeView").forward(request, response);
          
     }
     private void clearEmployeeComData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
          HttpSession session = request.getSession();
          ComplaintDao complaintDao = new ComplaintDao();
          
          request.setAttribute("selectedComplaint", null);
          
          String userId = (String) session.getAttribute("userId");
          List<Complaint> complaints = complaintDao.getComplaintOfEmpById(userId);
          session.setAttribute("complaintEmpList", complaints);
          
          request.getRequestDispatcher("dashboard.jsp?page=employeeView").forward(request, response);
     }
     
}

