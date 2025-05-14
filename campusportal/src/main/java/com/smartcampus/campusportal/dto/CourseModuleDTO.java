package com.smartcampus.campusportal.dto;

public class CourseModuleDTO {
    private String moduleName;
    private String moduleCode;

    public CourseModuleDTO(String moduleName, String moduleCode) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
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
