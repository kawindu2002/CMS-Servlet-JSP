package com.kp.dao;

import com.kp.model.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class UserDao {
     
     private final BasicDataSource ds;
     
     public UserDao(BasicDataSource ds) {
          this.ds = ds;
     }
     
     public String getNextUserId() throws SQLException {
          String sql = "SELECT id FROM users ORDER BY id DESC LIMIT 1";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql);
               ResultSet rs = stmt.executeQuery()) {
               
               if (rs.next()) {
                    String lastId = rs.getString("id"); // e.g. "U001"
                    String numberPart = lastId.substring(1); // remove 'U'
                    int next = Integer.parseInt(numberPart) + 1;
                    return String.format("U%03d", next);
               }
          }
          return "U001";  // default if no users yet
     }
     
     public User findByEmail(String email) throws SQLException {
          String sql = "SELECT * FROM users WHERE email = ?";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, email);
               
               try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                         return new User(
                              rs.getString("id"),
                              rs.getString("name"),
                              rs.getString("email"),
                              rs.getString("role"),
                              rs.getString("password")
                         );
                    }
               }
          }
          return null;  // no user found
     }
     
     public boolean saveUser(User user) throws SQLException {
          String sql = "INSERT INTO users (id, name, email, role, password) VALUES (?, ?, ?, ?, ?)";
          
          try (Connection conn = ds.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
               
               stmt.setString(1, user.getId());
               stmt.setString(2, user.getName());
               stmt.setString(3, user.getEmail());
               stmt.setString(4, user.getRole());
               stmt.setString(5, user.getPassword());
               
               return stmt.executeUpdate() > 0;
          }
     }
}
