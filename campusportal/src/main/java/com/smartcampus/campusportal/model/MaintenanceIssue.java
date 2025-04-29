package com.smartcampus.campusportal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenanceissue")  // Connects exactly to your DB table
public class MaintenanceIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IssueID")
    private Integer issueID;

    @Column(name = "Issue_Description", nullable = false)
    private String issueDescription;

    @Column(name = "ReportDate", nullable = false)
    private LocalDate reportDate;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "Reported_By", nullable = false)
    private Student reportedBy;

    @ManyToOne
    @JoinColumn(name = "Managed_By")
    private Admin managedBy;

    // ------------------------------
    // Default Constructor (required by JPA)
    public MaintenanceIssue() {
    }

    // ------------------------------
    // Constructor (optional, for easier creation)
    public MaintenanceIssue(String issueDescription, LocalDate reportDate, String status, Student reportedBy) {
        this.issueDescription = issueDescription;
        this.reportDate = reportDate;
        this.status = status;
        this.reportedBy = reportedBy;
    }

    // ------------------------------
    // Getters and Setters

    public Integer getIssueID() {
        return issueID;
    }

    public void setIssueID(Integer issueID) {
        this.issueID = issueID;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Student reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Admin getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Admin managedBy) {
        this.managedBy = managedBy;
    }
}
