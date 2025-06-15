package com.kp.dao;

import com.kp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
     public User getUser(int aid) {
          User user = new User();
          try{
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost:3306/cms_ijse";
               String sql = "select * from users where email=?";
               Connection conn = DriverManager.getConnection(url,"root","Ijse@1234");
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setInt(1,aid);
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
}
