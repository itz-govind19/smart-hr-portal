package com.demo.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class Department {

    @Id
    private Long id;

    private String name;

    private String description;

    private Long managerId; // reference to Employee in employee-service
}
