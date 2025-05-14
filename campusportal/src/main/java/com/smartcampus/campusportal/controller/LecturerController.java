package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.CourseModuleDTO;
import com.smartcampus.campusportal.dto.LectureLoginRequest;
import com.smartcampus.campusportal.model.CourseModule;
import com.smartcampus.campusportal.model.Lecturer;
import com.smartcampus.campusportal.model.Timetable;
import com.smartcampus.campusportal.repository.LecturerRepository;
import com.smartcampus.campusportal.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lecturer")
public class LecturerController {
    @Autowired
    private LecturerRepository lecturerRepo;
    @Autowired
    private TimetableRepository timetableRepository;

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
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<Lecturer>> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepo.findAll();
        return ResponseEntity.ok(lecturers);
    }
    @GetMapping("/modules/{lecturerId}")
    public List<CourseModuleDTO> getModulesForLecturer(@PathVariable Integer lecturerId) {
        List<Timetable> timetables = timetableRepository.findByLecturer_LecturerID(lecturerId);
        return timetables.stream()
                .map(tt -> new CourseModuleDTO(
                        tt.getModule().getModuleName(),
                        tt.getModule().getModuleCode()))
                .distinct()
                .collect(Collectors.toList());
    }

}