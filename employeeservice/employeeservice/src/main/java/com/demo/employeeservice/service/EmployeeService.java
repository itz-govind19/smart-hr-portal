package com.demo.employeeservice.service;

import com.demo.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee update(Long id, Employee updated);
    void delete(Long id);
    Employee getById(Long id);
    List<Employee> getAll();
}
