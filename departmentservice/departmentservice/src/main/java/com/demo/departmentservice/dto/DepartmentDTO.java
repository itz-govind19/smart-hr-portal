package com.demo.departmentservice.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private String name;
    private String description;
    private Long managerId;
}
