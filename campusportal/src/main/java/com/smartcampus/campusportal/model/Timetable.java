package com.smartcampus.campusportal.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Timetable")
public class Timetable {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timetableID;

    @ManyToOne
    @JoinColumn(name = "LecturerID", nullable = false)
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "ModuleID", nullable = false)
    private CourseModule module;

    private String day;

    private LocalTime time;


    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public CourseModule getModule() {
        return module;
    }

    public void setModule(CourseModule module) {
        this.module = module;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(Integer timetableID) {
        this.timetableID = timetableID;
    }
}
