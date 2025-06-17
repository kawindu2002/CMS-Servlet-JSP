package com.kp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Complaint {
     private String id;
     private String employee_id;
     private String title;
     private String description;
     private String status;
     private String remark;
     
     
     public Complaint(String id, String title, String description) {
          this.id = id;
          this.title = title;
          this.description = description;
     }
     
     public Complaint(String id, String employeeId, String title, String description) {
          this.id = id;
          this.employee_id = employeeId;
          this.title = title;
          this.description = description;
     }
     
     
     public static Complaint createWithStatusAndRemark(String id, String status, String remark) {
          Complaint c = new Complaint(id, "", ""); // call a base constructor
          c.status = status;       // set status manually
          c.remark = remark;       // set remark manually
          return c;
     }
     
}