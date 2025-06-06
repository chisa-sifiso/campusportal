package com.smartcampus.campusportal.model;


import jakarta.persistence.*;

@Entity  // Mark this class as a JPA entity
@Table(name = "Lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the LecturerID
    private Integer lecturerID;

    private String fname;
    private String lname;
    @Column(name = "Lecturer_Email")
    private String lecturerEmail; // Corrected column name
    private String password;

    // Getters and setters
    public Integer getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(Integer lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

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