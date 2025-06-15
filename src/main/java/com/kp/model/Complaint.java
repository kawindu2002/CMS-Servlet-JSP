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
     private int id;
     private int employee_id;
     private String title;
     private String description;
     private String status;
     private String remark;
     private String createdAt;
     private String updatedAt;
     
     @Override
     public String toString() {
          return "Complaint{" +
               "id=" + id +
               ", employee_id=" + employee_id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", status='" + status + '\'' +
               ", remark='" + remark + '\'' +
               ", createdAt='" + createdAt + '\'' +
               ", updatedAt='" + updatedAt + '\'' +
               '}';
     }
     
     public Complaint(int employee_id, String title, String description, String status, String remark, String createdAt, String updatedAt) {
          this.employee_id = employee_id;
          this.title = title;
          this.description = description;
          this.status = status;
          this.remark = remark;
          this.createdAt = createdAt;
          this.updatedAt = updatedAt;
     }
}
