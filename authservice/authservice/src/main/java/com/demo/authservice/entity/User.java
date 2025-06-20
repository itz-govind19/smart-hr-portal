package com.demo.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // ✅ Optional but good practice
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Recommended for databases
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // ✅ join table name
            joinColumns = @JoinColumn(name = "user_id"), // FK to user
            inverseJoinColumns = @JoinColumn(name = "role_name") // FK to role (since role's PK is name)
    )
    private Set<Role> roles = new HashSet<>();
}
