package com.kp.dao;

import com.kp.model.Complaint;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

public class ComplaintDao {
     
     private final BasicDataSource ds;
     
     public ComplaintDao(BasicDataSource ds) {
          this.ds = ds;
     }
     
     public String getNextComplaintId() throws SQLException {
          String sql = "SELECT id FROM complaints ORDER BY id DESC LIMIT 1";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql);
               ResultSet rs = stmt.executeQuery()) {
               
               if (rs.next()) {
                    String lastId = rs.getString("id"); // example: "C001"
                    String numberPart = lastId.substring(1); // remove "C"
                    int nextId = Integer.parseInt(numberPart) + 1;
                    return String.format("C%03d", nextId);
               }
          }
          
          return "C001"; // Default if no rows exist
     }
     
     public ArrayList<Complaint> getAllComplaints() throws SQLException {
          ArrayList<Complaint> complaints = new ArrayList<>();
          String sql = "SELECT * FROM complaints";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql);
               ResultSet rst = stmt.executeQuery()) {
               
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
          }
          
          return complaints;
     }
     
     public ArrayList<Complaint> getComplaintOfEmpById(String id) throws SQLException {
          ArrayList<Complaint> complaints = new ArrayList<>();
          
          String sql = "SELECT c.id, c.employee_id, c.title, c.description, c.status, c.admin_remark " +
               "FROM complaints c " +
               "JOIN users u ON c.employee_id = u.id " +
               "WHERE u.id = ?";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, id);
               
               try (ResultSet rst = stmt.executeQuery()) {
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
               }
          }
          
          return complaints;
     }
     
     public Complaint getComplaintByComId(String id) throws SQLException {
          String sql = "SELECT * FROM complaints WHERE id = ?";
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, id);
               try (ResultSet rst = stmt.executeQuery()) {
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
               }
          }
          return null;
     }
     
     public boolean saveComplaint(Complaint complaint) throws SQLException {
          String sql = "INSERT INTO complaints (id, employee_id, title, description) VALUES (?, ?, ?, ?)";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, complaint.getId());
               stmt.setString(2, complaint.getEmployee_id());
               stmt.setString(3, complaint.getTitle());
               stmt.setString(4, complaint.getDescription());
               
               return stmt.executeUpdate() > 0;
          }
     }
     
     public boolean updateComplaintForAdmin(Complaint complaint) throws SQLException {
          String sql = "UPDATE complaints SET status = ?, admin_remark = ? WHERE id = ?";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, complaint.getStatus());
               stmt.setString(2, complaint.getRemark());
               stmt.setString(3, complaint.getId());
               
               return stmt.executeUpdate() > 0;
          }
     }
     
     public boolean updateComplaintForEmp(Complaint complaint) throws SQLException {
          String sql = "UPDATE complaints SET title = ?, description = ? WHERE id = ?";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, complaint.getTitle());
               stmt.setString(2, complaint.getDescription());
               stmt.setString(3, complaint.getId());
               
               return stmt.executeUpdate() > 0;
          }
     }
     
     public boolean deleteComplaint(String id) throws SQLException {
          String sql = "DELETE FROM complaints WHERE id = ?";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, id);
               return stmt.executeUpdate() > 0;
          }
     }
     
}


