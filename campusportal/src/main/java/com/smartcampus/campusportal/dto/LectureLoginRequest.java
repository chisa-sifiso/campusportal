package com.smartcampus.campusportal.dto;

public class LectureLoginRequest {
    private String lecturerEmail;
    private String password;

    // Constructor
    public LectureLoginRequest(String lecturerEmail, String password) {
        this.lecturerEmail = lecturerEmail;
        this.password = password;
    }

    // Getters and Setters
    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
