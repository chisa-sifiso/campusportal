package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    // Custom query method to find Lecturer by Email and Password
    Lecturer findByLecturerEmailAndPassword(String lecturerEmail, String password);
    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
