package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.TimetableRequestDTO;
import com.smartcampus.campusportal.model.CourseModule;
import com.smartcampus.campusportal.model.Lecturer;
import com.smartcampus.campusportal.model.Timetable;
import com.smartcampus.campusportal.repository.CourseModuleRepository;
import com.smartcampus.campusportal.repository.LecturerRepository;
import com.smartcampus.campusportal.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/timetable")

public class TimetableController {

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createTimetable(@RequestBody TimetableRequestDTO dto) {
        Optional<CourseModule> moduleOpt = courseModuleRepository.findById(dto.getModuleId());
        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(dto.getLecturerId());

        if (moduleOpt.isEmpty() || lecturerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid module or lecturer ID.");
        }

        Timetable timetable = new Timetable();
        timetable.setDay(dto.getDay());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());
        timetable.setModule(moduleOpt.get());
        timetable.setLecturer(lecturerOpt.get());

        timetableRepository.save(timetable);

        return ResponseEntity.ok("Timetable entry created successfully.");
    }
    @GetMapping("/view-all")
    public ResponseEntity<List<Timetable>> viewAllTimetables() {
        List<Timetable> timetables = timetableRepository.findAll();
        return ResponseEntity.ok(timetables);
    }
}
