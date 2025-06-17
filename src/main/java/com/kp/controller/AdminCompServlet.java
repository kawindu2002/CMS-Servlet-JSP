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

@WebServlet("/admin")
public class AdminCompServlet extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String action = req.getParameter("action");

          if ("load".equals(action)) {
               try {
                    loadAdminComTable(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("clearForm".equals(action)) {
               try {
                    clearAdminComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("edit".equals(action)) {
               try {
                    editAdminComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          } else if ("update".equals(action)) {
               try {
                    updateAdminComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else if ("delete".equals(action)) {
               try {
                    deleteAdminComData(req, resp);
               } catch (Exception e) {
                    throw new RuntimeException(e);
               }
          }else {
               resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
          }
     }

     private void loadAdminComTable(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, ServletException {
          HttpSession session = req.getSession();

          ComplaintDao dao = new ComplaintDao();
          List<Complaint> list = dao.getAllComplaints();
          session.setAttribute("complaintAdminList", list);

          clearAdminComData(req,resp);

          resp.sendRedirect("dashboard.jsp?page=adminView");
     }

     private void updateAdminComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          
          Complaint selected = (Complaint) req.getSession().getAttribute("selectedComplaint");

          String id = selected.getId();
          String status = req.getParameter("status");
          String remark = req.getParameter("remark");
          
          if (id == null || id.isEmpty()) {
               resp.sendRedirect("dashboard.jsp?page=adminView&error=update_failed");
               return;
          }
          
          if (remark == null || remark.trim().isEmpty()) {
               remark = "add remark";  // default remark
          }
          

          if (status != null && !status.isEmpty() && remark != null && !remark.isEmpty()) {
              ComplaintDao complaintDao = new ComplaintDao();
               Complaint complaint = Complaint.createWithStatusAndRemark(id, status, remark);

              if (complaintDao.updateComplaintForAdmin(complaint)) {
                  resp.sendRedirect("dashboard.jsp?page=adminView&success=update_ok");
              } else {
                  resp.sendRedirect("dashboard.jsp?page=adminView&error=update_failed");
              }
              
          } else {
              System.out.println("💥 Status or Remark is missing!");
              resp.sendRedirect("dashboard.jsp?page=adminView&error=update_failed");
          }
     }

     private void deleteAdminComData(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException {
          String id = req.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao();
          if (complaintDao.deleteComplaint(id)) {
               resp.sendRedirect("dashboard.jsp?page=adminView&success=delete_ok");

          } else {
               resp.sendRedirect("dashboard.jsp?page=adminView&error=delete_failed");
          }
     }

     private void editAdminComData(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException, SQLException, ClassNotFoundException {

          String id = request.getParameter("id");
          ComplaintDao complaintDao = new ComplaintDao();
          Complaint selectedComplaint = complaintDao.getComplaintByComId(id);
          request.getSession().setAttribute("selectedComplaint", selectedComplaint);

          HttpSession session = request.getSession();
          List<Complaint> complaintList = complaintDao.getAllComplaints();
          session.setAttribute("complaintAdminList", complaintList);

          request.getRequestDispatcher("dashboard.jsp?page=adminView").forward(request, response);

     }


     private void clearAdminComData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
          
          ComplaintDao complaintDao = new ComplaintDao();

          request.getSession().setAttribute("selectedComplaint", null);

          List<Complaint> complaintList = complaintDao.getAllComplaints();
          request.getSession().setAttribute("complaintAdminList", complaintList);

          request.getRequestDispatcher("dashboard.jsp?page=adminView").forward(request, response);
     }

}

