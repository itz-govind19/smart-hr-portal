package com.demo.departmentservice.service;

import com.demo.departmentservice.entity.Department;
import com.demo.departmentservice.jdbc.DBSource;
import com.demo.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final DBSource dbSource;

    public Department create(Department dept) {
        Department save = departmentRepository.save(dept);
        dbSource.insertDepartment(save.getId(), dept.getName(), dept.getDescription(), dept.getManagerId());
        return save;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    public Department update(Long id, Department dept) {
        Department existing = getById(id);
        if (existing != null) {
            existing.setName(dept.getName());
            existing.setDescription(dept.getDescription());
            existing.setManagerId(dept.getManagerId());
            dbSource.insertDepartment(id, dept.getName(), dept.getDescription(), dept.getManagerId());
            return departmentRepository.save(existing);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
        dbSource.deleteDepartment(id);
    }

}
