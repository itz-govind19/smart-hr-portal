package com.demo.employeeservice.controller;

import com.demo.employeeservice.entity.Employee;
import com.demo.employeeservice.entity.EmployeeReport;
import com.demo.employeeservice.repo.EmployeeRepository;
import com.demo.employeeservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.demo.employeeservice.constant.Constants.BASE_URL;
import static com.demo.employeeservice.constant.Constants.FILE;

@RestController
@RequestMapping(BASE_URL+FILE)
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/upload/{employeeId}")
    public ResponseEntity<EmployeeReport> upload(
            @PathVariable Long employeeId,
            @RequestParam("file") MultipartFile file) throws IOException {

        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        return ResponseEntity.ok(fileService.saveReport(file, emp));
    }
}
