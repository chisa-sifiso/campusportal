package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.StudentLoginRequest;
import com.smartcampus.campusportal.model.Student;
import com.smartcampus.campusportal.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/get-all")
    public List<Student> getAll() {
        return repo.findAll();
    }

    @PostMapping("/add-stu")
    public Student save(@RequestBody Student student) {
        return repo.save(student);
    }

    @PostMapping("/login")
    public Student login(@RequestBody StudentLoginRequest loginRequest) {
        Student student = repo.findByStudentIDAndPassword(
                loginRequest.getStudentID(), loginRequest.getPassword()
        );

        if (student != null) {
            return student; // Success: Return student details
        } else {
            throw new RuntimeException("Invalid Student ID or Password"); // Failed login
        }         
}

//    @GetMapping("/count")
//    public long count() {
//        return repo.count();
//    }


}
