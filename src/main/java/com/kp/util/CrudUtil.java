package com.kp.util;

import java.sql.*;


public class CrudUtil {
     public static <T> T execute(String sql, Object... obj) throws SQLException, ClassNotFoundException {
          Class.forName("com.mysql.cj.jdbc.Driver");
          String url = "jdbc:mysql://localhost:3306/cms_ijse";
          Connection connection = DriverManager.getConnection(url, "root", "Ijse@1234");
          
          PreparedStatement pst = connection.prepareStatement(sql);
          
          for (int i = 0; i < obj.length; i++) {
               pst.setObject((i + 1), obj[i]);
          }
          
          if (sql.startsWith("select") || sql.startsWith("SELECT")) {
               ResultSet resultSet = pst.executeQuery();
               return (T) resultSet;
          } else {
               int i = pst.executeUpdate();
               boolean isSaved = i > 0;
               
               return (T) ((Boolean) isSaved);
          }
     }
     
     
     public static String getNextId(String query, String formatPattern, String defaultPattern) throws SQLException, ClassNotFoundException {
          ResultSet rst = CrudUtil.execute(query);
          if (rst.next()) {
               String lastId = rst.getString(1);
               String substring = lastId.substring(2);
               int i = Integer.parseInt(substring);
               int newIdIndex = i + 1;
               return String.format(formatPattern, newIdIndex);
          }
          return defaultPattern;
     }
}