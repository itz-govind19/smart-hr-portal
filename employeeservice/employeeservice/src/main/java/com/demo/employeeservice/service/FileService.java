package com.demo.employeeservice.service;

import com.demo.employeeservice.entity.Employee;
import com.demo.employeeservice.entity.EmployeeReport;
import com.demo.employeeservice.repo.EmployeeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final EmployeeReportRepository reportRepository;

    private final String uploadDir = "uploads/";

    public EmployeeReport saveReport(MultipartFile file, Employee employee) throws IOException {
        Files.createDirectories(Paths.get(uploadDir));

        String filePath = uploadDir + UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        EmployeeReport report = EmployeeReport.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath)
                .uploadedAt(LocalDateTime.now())
                .employee(employee)
                .build();

        return reportRepository.save(report);
    }
}
