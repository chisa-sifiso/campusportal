package com.smartcampus.campusportal.controller;

 // Assuming AdminLoginRequest is your DTO for admin login
import com.smartcampus.campusportal.dto.AdminLoginRequest;
import com.smartcampus.campusportal.model.Admin;
import com.smartcampus.campusportal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } else {


            return ResponseEntity.ok(admin);  // Return Admin data if login is successful

        }
    }
}
