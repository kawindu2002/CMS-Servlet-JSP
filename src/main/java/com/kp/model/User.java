package com.kp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
     private int id;
     private String name;
     private String email;
     private String role;
     private String password;
     
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
