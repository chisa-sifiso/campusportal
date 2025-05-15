package com.smartcampus.campusportal.model;

import jakarta.persistence.*;
import java.time.LocalTime;

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

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    public Integer getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(Integer timetableID) {
        this.timetableID = timetableID;
    }

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

//    public LocalTime getTime() {
//        return time;
//    }
//
//    public void setTime(LocalTime time) {
//        this.time = time;
//    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

//    private LocalTime time;



    // Getters and Setters



}
