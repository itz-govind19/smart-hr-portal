package com.demo.authservice.config;

import com.demo.authservice.entity.Role;
import com.demo.authservice.repositoy.RoleRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepo roleRepository;

    @PostConstruct
    public void insertDefaultRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("MANAGER"));
            roleRepository.save(new Role("EMPLOYEE"));
            System.out.println("✅ Default roles seeded to database");
        } else {
            System.out.println("ℹ️ Roles already exist — skipping seeding");
        }
    }
}
