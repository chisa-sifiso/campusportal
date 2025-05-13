package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.MaintenanceIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MaintenanceIssueRepository extends JpaRepository<MaintenanceIssue, Integer> {
    // Later we can add custom queries if needed
    long countByStatus(String status);
    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
