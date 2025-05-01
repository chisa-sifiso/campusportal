package com.smartcampus.campusportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification_Recipient")
public class NotificationRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipientID;

    @ManyToOne
    @JoinColumn(name = "notificationID", nullable = false)
    private Notification notification;

    @Column(nullable = false)
    private String recipientType; // 'student' or 'lecturer'

    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecturerID")
    private Lecturer lecturer;

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public Integer getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(Integer recipientID) {
        this.recipientID = recipientID;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
