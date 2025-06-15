package com.kp.dao;

import com.kp.model.User;

import java.sql.*;

public class UserDao {
     
     public User getUser(String email) {
          User user = new User();
          try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               String sql = "select * from users where email=?";
               Connection conn = DriverManager.getConnection(url,"root","Ijse@1234");
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1,email);
               ResultSet rs = stmt.executeQuery();
               if(rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setPassword(rs.getString("password"));
               }
               
          } catch (Exception e) {
               System.out.println(e.getMessage());
          }
          return user;
     }
     
     public boolean saveUser(User user) {
          boolean isSaved = false;
          
          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               Connection conn = DriverManager.getConnection(url, "root", "Ijse@1234");
               
               String sql = "INSERT INTO users (name, email, role, password) VALUES (?, ?, ?, ?)";
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1, user.getName());
               stmt.setString(2, user.getEmail());
               stmt.setString(3, user.getRole());
               stmt.setString(4, user.getPassword());
               
               int rows = stmt.executeUpdate();
               isSaved = rows > 0;
               
               conn.close();
               
          } catch (Exception e) {
               System.out.println("Error saving user: " + e.getMessage());
          }
          return isSaved;
     }
     
}

