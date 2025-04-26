package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.LoginRequest;
import com.smartcampus.campusportal.model.Student;
import com.smartcampus.campusportal.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        return repo.save(student);
    }

    @PostMapping("/login")
    public Student login(@RequestBody LoginRequest loginRequest) {
        Student student = repo.findByStudentIDAndPassword(
                loginRequest.getStudentID(), loginRequest.getPassword()
        );

        if (student != null) {
            return student; // Success: Return student details
        } else {
            throw new RuntimeException("Invalid Student ID or Password"); // Failed login
        }
}}
