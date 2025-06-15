package com.kp.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class User {
     private int id;
     private String name;
     private String email;
     private String role;
     private String password;
     
     public User(String name, String email, String role,String password) {
          this.name = name;
          this.email = email;
          this.role = role;
          this.password = password;
          
     }
     
     @Override
     public String toString() {
          return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               ", password='" + password + '\'' +
               '}';
     }
}
