package com.smartcampus.campusportal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private String moduleCode;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;



    public AppointmentDTO(String moduleCode, LocalDate date, LocalTime startTime, LocalTime endTime, String status) {
        this.moduleCode = moduleCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public AppointmentDTO() {
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    // Getters and setters
}
