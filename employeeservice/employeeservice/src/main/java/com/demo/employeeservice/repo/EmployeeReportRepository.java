package com.demo.employeeservice.repo;

import com.demo.employeeservice.entity.EmployeeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReportRepository extends JpaRepository<EmployeeReport, Long> {}
