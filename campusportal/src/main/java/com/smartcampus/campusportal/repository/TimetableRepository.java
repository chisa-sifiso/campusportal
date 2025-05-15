package com.smartcampus.campusportal.repository;

import com.smartcampus.campusportal.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    List<Timetable> findByLecturer_LecturerID(Integer lecturerID);
    List<Timetable> findByModule_ModuleIDIn(List<Long> moduleIds);
}
