package com.smartcampus.campusportal.dto;

public class LoginRequest {
    private Integer studentID;
    private String password;

    // Getters and Setters
    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
}}
