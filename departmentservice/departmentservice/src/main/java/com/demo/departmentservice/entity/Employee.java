package com.demo.departmentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    private Long id;

    private String name;
    private String email;
    private Long departmentId;

//    private Employee manager; // Self-referencing for manager
}
