package com.kp.dao;

import com.kp.model.Complaint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDao {
     public List<Complaint> getAllComplaints() {
          List<Complaint> complaints = new ArrayList<>();
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "SELECT * FROM complaints";
               PreparedStatement stmt = conn.prepareStatement(sql);
               ResultSet rs = stmt.executeQuery();
               
               while (rs.next()) {
                    Complaint complaint = new Complaint();
                         complaint.setId(rs.getInt("id"));
                         complaint.setEmployee_id(rs.getInt("employee_id"));
                         complaint.setTitle(rs.getString("title"));
                         complaint.setDescription(rs.getString("description"));
                         complaint.setStatus(rs.getString("status"));
                         complaint.setRemark(rs.getString("admin_remark"));
                         complaint.setCreatedAt(rs.getTimestamp("created_at").toString());
                         complaint.setUpdatedAt(rs.getTimestamp("updated_at").toString());
                    
                    complaints.add(complaint);
               }
               conn.close();
          } catch (Exception e) {
               System.out.println("Error fetching complaints: " + e.getMessage());
          }
          
          return complaints;
     }
     
     
     public  List<Complaint>  getComplaintOfEmpByEmail(String email) {
          List<Complaint> complaints = new ArrayList<>();
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "SELECT c.* FROM complaints c JOIN users u ON c.employee_id = u.id WHERE u.email = ?";
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1, email);
               ResultSet rs = stmt.executeQuery();
               
               while (rs.next()) {
                    Complaint complaint = new Complaint();
                         complaint.setId(rs.getInt("id"));
                         complaint.setEmployee_id(rs.getInt("employee_id"));
                         complaint.setTitle(rs.getString("title"));
                         complaint.setDescription(rs.getString("description"));
                         complaint.setStatus(rs.getString("status"));
                         complaint.setRemark(rs.getString("admin_remark"));
                         complaint.setCreatedAt(rs.getTimestamp("created_at").toString());
                         complaint.setUpdatedAt(rs.getTimestamp("updated_at").toString());
                    
                    complaints.add(complaint);
               }
               conn.close();
          } catch (Exception e) {
               System.out.println("Error fetching complaints: " + e.getMessage());
          }
          
          return complaints;
     }
     
     public Boolean saveComplaint(Complaint complaint) {
          boolean isSaved = false;
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "INSERT INTO complaints (employee_id, title, description, status, admin_remark ) VALUES (?, ?, ?, ?,?)";
               PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, complaint.getEmployee_id());
                    stmt.setString(2, complaint.getTitle());
                    stmt.setString(3, complaint.getDescription());
                    stmt.setString(4, complaint.getStatus());
                    stmt.setString(5, complaint.getRemark());
                    
               int rows = stmt.executeUpdate();
               isSaved = rows > 0;
               
               conn.close();
               
          } catch (Exception e) {
               System.out.println("Error saving complaint: " + e.getMessage());
          }
          return isSaved;
     }
     
     public boolean updateComplaint(Complaint complaint) {
          boolean isUpdated = false;
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "UPDATE complaints SET title = ?, description = ?, status = ?, admin_remark = ? WHERE id = ?";
               PreparedStatement stmt = conn.prepareStatement(sql);
               
                    stmt.setString(1, complaint.getTitle());
                    stmt.setString(2, complaint.getDescription());
                    stmt.setString(3, complaint.getStatus());
                    stmt.setString(4, complaint.getRemark());
                    stmt.setInt(5, complaint.getId());
               
               int rows = stmt.executeUpdate();
               isUpdated = rows > 0;
               
               conn.close();
               
          } catch (Exception e) {
               System.out.println("Error updating complaint: " + e.getMessage());
          }
          return isUpdated;
     }
     
     
     public boolean deleteComplaint(int id) {
          boolean isDeleted = false;
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "DELETE FROM complaints WHERE id = ?";
               PreparedStatement stmt = conn.prepareStatement(sql);
               
               stmt.setInt(1, id);
               
               int rows = stmt.executeUpdate();
               isDeleted = rows > 0;
               
               conn.close();
               
          } catch (Exception e) {
               System.out.println("Error deleting complaint: " + e.getMessage());
          }
          return isDeleted;
     }
     
}


