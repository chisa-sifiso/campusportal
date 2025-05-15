package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.model.NotificationRecipient;
import com.smartcampus.campusportal.repository.NotificationRecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/student")
public class NotificationController {
    @Autowired
    private NotificationRecipientRepository recipientRepository;

    @GetMapping("/{studentId}")
    public List<NotificationRecipient> getNotificationsForStudent(@PathVariable Integer studentId) {
        return recipientRepository.findByStudent_StudentID(studentId);
    }
}
