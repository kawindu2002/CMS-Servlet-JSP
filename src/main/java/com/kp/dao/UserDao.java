package com.kp.dao;

import com.kp.model.User;
import com.kp.util.CrudUtil;

import java.sql.*;

public class UserDao {
     
     public String getNextUserId() throws SQLException, ClassNotFoundException {
          String query = "select id from users order by id desc limit 1";
          return CrudUtil.getNextId(query,"U%03d","U001");
     }
     
     public User findByEmail(String email) throws SQLException, ClassNotFoundException {
          ResultSet rst = CrudUtil.execute("select * from users where email=?", email);
          
          if (rst.next()) {
               return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
               );
          }
          return null;
     }
     
     public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
          return CrudUtil.execute(
               "INSERT INTO users (id,name, email, role, password) VALUES (?, ?, ?, ?,?)",

               user.getId(),
               user.getName(),
               user.getEmail(),
               user.getRole(),
               user.getPassword()
          
          );
     }
}

