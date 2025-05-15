package com.smartcampus.campusportal.dto;

import java.time.LocalTime;

public class TimetableResponseDTO {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String moduleName;
    private String lecturerName;

    public TimetableResponseDTO(String day, LocalTime startTime, LocalTime endTime, String moduleName, String lecturerName) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.moduleName = moduleName;
        this.lecturerName = lecturerName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
