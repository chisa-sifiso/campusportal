package com.smartcampus.campusportal.controller;


import com.smartcampus.campusportal.dto.MaintenanceIssueRequest;
import com.smartcampus.campusportal.model.Admin;
import com.smartcampus.campusportal.model.MaintenanceIssue;
import com.smartcampus.campusportal.model.Student;
import com.smartcampus.campusportal.repository.MaintenanceIssueRepository;
import com.smartcampus.campusportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class MaintenanceIssueController {
    @Autowired
    private MaintenanceIssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Create new issue (Student reports)
    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody MaintenanceIssueRequest issueRequest) {
        Student student = studentRepository.findById(issueRequest.getReportedByStudentId())
                .orElse(null);

        if (student == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }
        Admin admin = new Admin();
        admin.setAdminID(1);

        MaintenanceIssue issue = new MaintenanceIssue();
        issue.setIssueDescription(issueRequest.getIssueDescription());
        issue.setReportedBy(student);
        issue.setReportDate(LocalDate.now());
        issue.setStatus("Open");
        issue.setManagedBy(admin);

        MaintenanceIssue savedIssue = issueRepository.save(issue);
        return ResponseEntity.ok(savedIssue);
    }

    // View all issues
    @GetMapping
    public List<MaintenanceIssue> getAllIssues() {
        return issueRepository.findAll();
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateIssueStatus(
            @PathVariable Integer id,
            @RequestParam String newStatus) {

        MaintenanceIssue issue = issueRepository.findById(id).orElse(null);

        if (issue == null) {
            return ResponseEntity.status(404).body("Issue not found");
        }

        issue.setStatus(newStatus);
        issueRepository.save(issue);

        return ResponseEntity.ok("Issue status updated to: " + newStatus);
    }
}
