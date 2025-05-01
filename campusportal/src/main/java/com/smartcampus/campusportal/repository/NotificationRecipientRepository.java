package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.NotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, Integer> {
    List<NotificationRecipient> findByStudent_StudentID(Integer studentId);
}
