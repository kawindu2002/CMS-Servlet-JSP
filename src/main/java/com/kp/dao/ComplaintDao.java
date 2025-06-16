package com.kp.dao;

import com.kp.model.Complaint;
import com.kp.model.User;
import com.kp.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class ComplaintDao {
     
     public String getNextComplaintId() throws SQLException, ClassNotFoundException {
          String query = "select id from complaints order by id desc limit 1";
          return CrudUtil.getNextId(query,"C%03d","C001");
     }
     
     
     public ArrayList<Complaint> getAllComplaints() throws SQLException, ClassNotFoundException {
          ResultSet rst = CrudUtil.execute("SELECT * FROM complaints");
          
          ArrayList<Complaint> complaints = new ArrayList<>();
          
          while (rst.next()) {
               Complaint complaint = new Complaint(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
                    
               );
               complaints.add(complaint);
          }
          return complaints;
     }
     
     public ArrayList<Complaint> getComplaintOfEmpById(String id) throws SQLException, ClassNotFoundException {
          ArrayList<Complaint> complaints = new ArrayList<>();
          
          ResultSet rst = CrudUtil.execute(
               "SELECT c.id, c.employee_id, c.title, c.description, c.status, c.admin_remark " +
                    "FROM complaints c " +
                    "JOIN users u ON c.employee_id = u.id " +
                    "WHERE u.id = ?", id);
          
          while (rst.next()) {
               complaints.add(new Complaint(
                    rst.getString("id"),
                    rst.getString("employee_id"),
                    rst.getString("title"),
                    rst.getString("description"),
                    rst.getString("status"),
                    rst.getString("admin_remark")
               ));
          }
          
          return complaints;
     }
     
     public Complaint getComplaintByComId(String id) throws SQLException, ClassNotFoundException {
          ResultSet rst = CrudUtil.execute("SELECT * FROM complaints WHERE id = ?", id);
          
          if (rst.next()) {
               return new Complaint(
                    rst.getString("id"),
                    rst.getString("employee_id"),
                    rst.getString("title"),
                    rst.getString("description"),
                    rst.getString("status"),
                    rst.getString("admin_remark")
               );
          }
          
          // If no complaint is found, return null
          return null;
     }
     
     
     public boolean saveComplaint(Complaint complaint)  throws SQLException, ClassNotFoundException {
          return CrudUtil.execute(
               "INSERT INTO complaints (id,employee_id,title,description) VALUES (?, ?, ?,?)",
               
               complaint.getId(),
               complaint.getEmployee_id(),
               complaint.getTitle(),
               complaint.getDescription()
          
          );
     }
     
     public boolean updateComplaintForEmp(Complaint complaint) throws SQLException, ClassNotFoundException {
          return CrudUtil.execute(
               "UPDATE complaints SET title = ?, description = ? WHERE id = ?",
               complaint.getTitle(),
               complaint.getDescription(),
               complaint.getId()
          );
     }
     
     public boolean updateComplaintForAdmin(Complaint complaint) throws SQLException, ClassNotFoundException {
          return CrudUtil.execute(
               "UPDATE complaints SET title = ?, description = ?, status = ?, admin_remark = ? WHERE id = ?",
               complaint.getTitle(),
               complaint.getDescription(),
               complaint.getStatus(),
               complaint.getRemark(),
               complaint.getId()
          );
     }
     
     public boolean deleteComplaint(String id) throws SQLException, ClassNotFoundException {
          return CrudUtil.execute("DELETE FROM complaints WHERE id = ?", id);
     }
     
     
}


