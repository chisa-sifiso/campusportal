package com.smartcampus.campusportal.controller;

 // Assuming AdminLoginRequest is your DTO for admin login
import com.smartcampus.campusportal.dto.AdminLoginRequest;
import com.smartcampus.campusportal.model.Admin;
import com.smartcampus.campusportal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    // Admin Login API
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginRequest adminLoginRequest) {
        // Find Admin by AdminID and Password
        Admin admin = adminRepo.findByAdminIDAndPassword(
                adminLoginRequest.getAdminID(), adminLoginRequest.getPassword()
        );

        // If admin is found with valid credentials
        if (admin != null) {
            return ResponseEntity.ok(admin); // ✅ Correct: valid admin
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); // ❌ Invalid
        }
    }
}