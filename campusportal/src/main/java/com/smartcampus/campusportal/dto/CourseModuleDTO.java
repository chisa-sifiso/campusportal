package com.smartcampus.campusportal.dto;

public class CourseModuleDTO {
    private String moduleName;
    private String moduleCode;
    private Integer moduleID;

    public CourseModuleDTO(String moduleName, String moduleCode) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }
    public CourseModuleDTO(Integer moduleID, String moduleName, String moduleCode) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }
    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}
