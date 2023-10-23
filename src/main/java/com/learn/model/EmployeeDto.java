package com.learn.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
  private String name;
  private String id;
  private Float salary;
}
