package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByModule_ModuleIDAndDate(Integer moduleID, LocalDate date);
}
