package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.MaintenanceIssueRequest;
import com.smartcampus.campusportal.model.*;
import com.smartcampus.campusportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/api/report-issues")
public class MaintenanceIssueController {

    @Autowired
    private MaintenanceIssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationRecipientRepository notificationRecipientRepository;

    /**
     * Student reports a new maintenance issue.
     * Automatically assigns Admin with ID = 1 to manage the issue.
     */
    @PostMapping
    public ResponseEntity<?> createIssue(@RequestBody MaintenanceIssueRequest issueRequest) {
        Student student = studentRepository.findById(issueRequest.getReportedByStudentId()).orElse(null);

        if (student == null) {
            return ResponseEntity.badRequest().body("Student not found.");
        }

        // Hardcoded assignment to Admin ID 1
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

    /**
     * Retrieve all maintenance issues reported in the system.
     */
    @GetMapping("/get-all")
    public List<MaintenanceIssue> getAllIssues() {
        return issueRepository.findAll();
    }

    /**
     * Update the status of a maintenance issue.
     * If the status is changed to 'Closed', notify the student who reported it.
     */
    @PutMapping("/{id}/status-feedback")
    public ResponseEntity<?> updateIssueStatus(
            @PathVariable Integer id,
            @RequestParam String newStatus) {

        MaintenanceIssue issue = issueRepository.findById(id).orElse(null);
        if (issue == null) {
            return ResponseEntity.status(404).body("Issue with ID " + id + " not found.");
        }

        issue.setStatus(newStatus);
        issueRepository.save(issue);

        // Send notification if issue is marked as 'Closed'
        if ("Closed".equalsIgnoreCase(newStatus)) {
            String message = "Your reported maintenance issue has been resolved: " + issue.getIssueDescription();

            // Save notification
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setDate(LocalDate.now());
            notificationRepository.save(notification);

            // Link notification to student
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setNotification(notification);
            recipient.setRecipientType("student");
            recipient.setStudent(issue.getReportedBy());
            notificationRecipientRepository.save(recipient);
        }

        return ResponseEntity.ok("Issue status updated to: " + newStatus);
    }
}
