package com.demo.departmentservice.service;

import com.demo.departmentservice.entity.Department;
import com.demo.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department create(Department dept) {
        return departmentRepository.save(dept);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    public Department update(Long id, Department dept) {
        Department existing = getById(id);
        existing.setName(dept.getName());
        existing.setDescription(dept.getDescription());
        existing.setManagerId(dept.getManagerId());
        return departmentRepository.save(existing);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

}
