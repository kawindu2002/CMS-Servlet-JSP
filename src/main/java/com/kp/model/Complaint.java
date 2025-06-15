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
     
     
}