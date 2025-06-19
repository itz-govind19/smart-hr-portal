package com.demo.authservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {

    @Id
    private String name; // ADMIN, MANAGER, EMPLOYEE
}
