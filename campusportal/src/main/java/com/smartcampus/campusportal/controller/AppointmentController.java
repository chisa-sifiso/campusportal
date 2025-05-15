package com.smartcampus.campusportal.controller;

import com.smartcampus.campusportal.dto.AppointmentDTO;
import com.smartcampus.campusportal.dto.AppointmentRequestDTO;
import com.smartcampus.campusportal.model.*;
import com.smartcampus.campusportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/by-module-and-date")
    public List<Appointment> getAppointmentsByModuleAndDate(
            @RequestParam Integer moduleID,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentRepository.findByModule_ModuleIDAndDate(moduleID, date);
    }


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationRecipientRepository notificationRecipientRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDTO dto) {
        Optional<Student> studentOpt = studentRepository.findById(dto.getStudentId());
        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(dto.getLecturerId());
        Optional<CourseModule> moduleOpt = courseModuleRepository.findById(dto.getModuleId());

        if (studentOpt.isEmpty() || lecturerOpt.isEmpty() || moduleOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid student, lecturer, or module ID.");
        }

        Appointment appointment = new Appointment();
        appointment.setStudent(studentOpt.get());
        appointment.setLecturer(lecturerOpt.get());
        appointment.setModule(moduleOpt.get());
        appointment.setDate(dto.getDate());
        appointment.setStartTime(dto.getStartTime());
        appointment.setEndTime(dto.getEndTime());
        appointment.setStatus("Pending");
        appointment.setTimeSlot(dto.getStartTime());

        Appointment saved = appointmentRepository.save(appointment);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable Integer id, @RequestParam String status) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.badRequest().body("Appointment not found.");
        }

        Appointment appointment = optionalAppointment.get();
        appointment.setStatus(status);
        appointmentRepository.save(appointment);

        // ✅ Send notification based on new status
        String message;
        if ("Accept".equalsIgnoreCase(status)) {
            message = "Your session for module " + appointment.getModule().getModuleName() + " has been approved.";
        } else if ("Decline".equalsIgnoreCase(status)) {
            message = "Your session for module " + appointment.getModule().getModuleName() + " was not approved.";
        } else {
            return ResponseEntity.ok("Status updated without notification.");
        }

        // ✅ Save notification
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDate(LocalDate.now());
        notificationRepository.save(notification);

        // ✅ Link notification to student
        NotificationRecipient recipient = new NotificationRecipient();
        recipient.setNotification(notification);
        recipient.setRecipientType("student");
        recipient.setStudent(appointment.getStudent());
        notificationRecipientRepository.save(recipient);

        return ResponseEntity.ok("Appointment status updated and notification sent.");
    }
    @GetMapping("/gellAll-appointments")
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setDate(appointment.getDate());
            dto.setModuleCode(appointment.getModule().getModuleCode());
            dto.setDate(appointment.getDate());
            dto.setStartTime(appointment.getStartTime());
            dto.setEndTime(appointment.getEndTime());
            dto.setStatus(appointment.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
}
