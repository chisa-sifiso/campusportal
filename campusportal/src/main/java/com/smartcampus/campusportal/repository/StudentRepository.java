package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentIDAndPassword(Integer studentID, String password);
    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
