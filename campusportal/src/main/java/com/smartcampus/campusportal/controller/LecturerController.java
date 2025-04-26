package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.LectureLoginRequest;
import com.smartcampus.campusportal.model.Lecturer;
import com.smartcampus.campusportal.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecturer")
public class LecturerController {
    @Autowired
    private LecturerRepository lecturerRepo;

    @PostMapping("/login")
    public ResponseEntity<?> loginLecturer(@RequestBody LectureLoginRequest loginRequest) {
        // Validate lecturer credentials
        Lecturer lecturer = lecturerRepo.findByLecturerEmailAndPassword(
                loginRequest.getLecturerEmail(), loginRequest.getPassword()
        );

        if (lecturer != null) {
            // Return the lecturer details if credentials are valid
            return ResponseEntity.ok(lecturer);
        } else {
            // Return unauthorized response if credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }
    }}