package com.smartcampus.campusportal.dto;

public class AdminLoginRequest {
    private Integer adminID;
    private String password;

    // Getters and Setters
    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
