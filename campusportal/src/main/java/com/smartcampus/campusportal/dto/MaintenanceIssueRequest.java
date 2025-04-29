package com.smartcampus.campusportal.dto;

public class MaintenanceIssueRequest {
    private Integer reportedByStudentId;
    private String issueDescription;

    // Getters and Setters
    public Integer getReportedByStudentId() {
        return reportedByStudentId;
    }

    public void setReportedByStudentId(Integer reportedByStudentId) {
        this.reportedByStudentId = reportedByStudentId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
}
