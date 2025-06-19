package com.demo.employeeservice.service;

import com.demo.employeeservice.entity.Employee;
import com.demo.employeeservice.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepo;

    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = employeeRepo.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        existing.setManager(updated.getManager());
        return employeeRepo.save(existing);
    }

    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    public Employee getById(Long id) {
        return employeeRepo.findById(id).orElseThrow();
    }

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }
}
